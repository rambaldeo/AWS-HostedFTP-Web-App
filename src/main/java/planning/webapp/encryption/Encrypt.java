package planning.webapp.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Encrypt {

	private static final Random RANDOM = new SecureRandom();
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;
	private final static Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");

	public static String getNextSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	public static String encrypt(String password, String salt) throws SQLException {
		char[] passwordChars = password.toCharArray();

		String saltedPassword = saltPassword(password, salt);
		String hashedPassword = hashPassword(saltedPassword);

		return saltedPassword;
	}

	public static String saltPassword(String password, String salt) {

		char[] passwordChar = password.toCharArray();
		PBEKeySpec spec = new PBEKeySpec(passwordChar, Base64.getDecoder().decode(salt), ITERATIONS, KEY_LENGTH);
		Arrays.fill(passwordChar, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return Base64.getEncoder().encodeToString(skf.generateSecret(spec).getEncoded());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AssertionError("Error while encrpting password");
		} finally {
			spec.clearPassword();
		}
	}

	public static String hashPassword(String saltPassword) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] messageDigest = md.digest(saltPassword.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : messageDigest) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isExpectedPassword(String dbPassword, String enteredPassword, String saltKey)
			throws ClassNotFoundException, SQLException {
		String encryptEnteredPassword = encrypt(enteredPassword, saltKey);

		if (encryptEnteredPassword.equals(dbPassword)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isTextValid(String userPassword, String verifyPassword) {
		if(Objects.equals(userPassword, verifyPassword)) {
			try {
				return textPattern.matcher(userPassword).matches();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			return false;
		}

		return false;
	}

}
