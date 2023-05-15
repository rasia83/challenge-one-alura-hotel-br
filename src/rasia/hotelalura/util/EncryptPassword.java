package rasia.hotelalura.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncryptPassword {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		String passwordToHash = "admin";
		System.out.println("passwordToHash " + passwordToHash);
		String salt = getSalt();
		String securePassword = get_SHA_1_SecurePassword(passwordToHash, salt);
		System.out.println("SHA_1 " + securePassword);

		securePassword = get_SHA_256_SecurePassword(passwordToHash, salt);
		System.out.println("SHA_256 " + securePassword);

		securePassword = get_SHA_384_SecurePassword(passwordToHash, salt);
		System.out.println("SHA_384 " + securePassword);

		securePassword = get_SHA_512_SecurePassword(passwordToHash, salt);
		System.out.println("SHA_512 " + securePassword);
	}

	public static final String encrypt(String passwordToHash) throws NoSuchAlgorithmException {
		String salt = getSalt();
		return get_SHA_256_SecurePassword(passwordToHash, salt);
	}

	private static String get_SHA_1_SecurePassword(String passwordToHash, String salt) {
		return getSecurePassword(passwordToHash, salt, "SHA-1");
	}

	private static String get_SHA_256_SecurePassword(String passwordToHash, String salt) {
		return getSecurePassword(passwordToHash, salt, "SHA-256");
	}

	private static String get_SHA_384_SecurePassword(String passwordToHash, String salt) {
		return getSecurePassword(passwordToHash, salt, "SHA-384");
	}

	private static String get_SHA_512_SecurePassword(String passwordToHash, String salt) {
		return getSecurePassword(passwordToHash, salt, "SHA-512");
	}

	private static String getSecurePassword(String passwordToHash, String salt, String algorithm) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(salt.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100).substring(1));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Add salt
	private static String getSalt() throws NoSuchAlgorithmException {
		/*
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		//System.out.println("Salt " + salt.toString());
		return salt.toString();
		*/
		return "Rasia";
	}

}
