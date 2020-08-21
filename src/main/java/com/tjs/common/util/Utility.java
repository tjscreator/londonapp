/**
 * 
 */
package com.tjs.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tjs.common.enums.EnumType;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.logger.LoggerService;
import com.tjs.common.view.KeyValueView;
import com.tjs.harbor.exception.HarborException;

/**
 * @author Dhruvang
 *
 */
public class Utility {

	private Utility() {
		//
	}

	private static ThreadLocal<MessageDigest> md5MessageDigest = new ThreadLocal<MessageDigest>() {
		@Override
		protected MessageDigest initialValue() {
			try {
				return MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
		}
	};

	private static ThreadLocal<MessageDigest> sha512MessageDigest = new ThreadLocal<MessageDigest>() {
		@Override
		protected MessageDigest initialValue() {
			try {
				return MessageDigest.getInstance("SHA-512");
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
		}
	};

	/**
	 * This methods is used to convert EnumType list into KeyValueView list.
	 * 
	 * @param list
	 *            list of EnumType
	 * @return listView list of KeyValueView
	 */
	public static List<KeyValueView> toKeyValueView(EnumType[] list) {
		List<KeyValueView> listView = new ArrayList<>(list.length);
		for (EnumType enumType : list) {
			// listView.add(KeyValueView.create((long) enumType.getId(),
			// enumType.getName().toUpperCase()));
		}
		Collections.sort(listView, KeyValueView.idComparator);
		return listView;
	}

	public static SecureRandom rnd = new SecureRandom();

	/**
	 * This method is used to create a new folder on given path. If folder is
	 * already exist then return's the current folder.
	 * 
	 * @param pathName
	 *            path where folder needs to be created
	 * @return {@link File}
	 */
	public static File createNewFolder(String pathName) {
		File dir = new File(pathName);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		return dir;
	}

	/**
	 * This method is used to create a new file on give path.
	 * 
	 * @param pathName
	 *            path where file needs to be created
	 * @return {@link File}
	 * @throws IOException
	 */
	public static File createNewFile(String pathName) throws IOException {
		File file = new File(pathName);
		if (file.exists()) {
			return null;
		}
		file.createNewFile();
		return file;
	}

	/**
	 * This method gives file name without extension
	 * 
	 * @param file
	 * @return fileName without extension
	 */
	public static String getFileNameWithOutExtension(File file) {
		return FilenameUtils.removeExtension(file.getName());
	}

	/**
	 * This methods is used to convert EnumType list into KeyValueView list.
	 * 
	 * @param list
	 *            list of EnumType
	 * @return listView list of KeyValueView
	 */
	public static List<KeyValueView> toKeyValueView(EnumSet list) {
		List<KeyValueView> listView = new ArrayList<>(list.size());

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			EnumType object = (EnumType) iterator.next();
			// listView.add(KeyValueView.create((long) object.getId(),
			// object.getName().toUpperCase()));
		}

		Collections.sort(listView, KeyValueView.idComparator);
		return listView;
	}

	public static void copyFileUsingFileChannels(File source, File dest) throws IOException {

		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}

	/**
	 * This method is used to generating random Token.
	 * 
	 * @param otpLength
	 * @return String number
	 */
	public static String generateToken(int tokenLength) {
		return RandomStringUtils.randomAlphanumeric(tokenLength);
	}

	/**
	 * This method is used to generating otp.
	 * 
	 * @param otpLength
	 * @return
	 */
	public static String generateOtp(int otpLength) {
		return RandomStringUtils.randomNumeric(otpLength);
	}

	public static boolean isOtpExpired(Date otpGeneratedDate) {
		/*
		 * if(otpExpired == CommonStatus.YES.getId()){ return true; }
		 */
		Date currentDate = new Date();
		// return
		// currentDate.compareTo(Utility.getDateAfterHours(otpGeneratedDate,
		// SystemConfig.getOtpExpiredTimeInHours())) > 0;
		return currentDate.compareTo(Utility.getDateAfterHours(otpGeneratedDate, 1)) > 0;
	}

	public static Date getDateAfterHours(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hours);
		return calendar.getTime();
	}

	/**
	 * This method is used to generating random Token.
	 * 
	 * @param otpLength
	 * @return String number
	 */
	public static String generateCode(int codeLength) {
		return RandomStringUtils.randomAlphanumeric(codeLength);
	}

	public static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			String format = "%0" + paddingLength + "d";
			return String.format(format, 0) + hex;
		} else {
			return hex;
		}
	}

	public static String getMD5(String hash) {
		md5MessageDigest.get().update(hash.getBytes());
		return toHex(md5MessageDigest.get().digest());
	}

	/**
	 * This method is used to generating hash value of given string using SHA512
	 * hashing algorithm
	 * 
	 * @param value
	 * @return String
	 */
	public static String getSHA512(String value) {
		sha512MessageDigest.get().update((value).getBytes());
		return Utility.toHex(sha512MessageDigest.get().digest());
	}

	public static String generateRandomNumber(Integer length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++)
			sb.append(Constant.DIGITS.charAt(rnd.nextInt(Constant.DIGITS.length())));
		return sb.toString();
	}

	public static String generateSession(String txtUserSession) {
		return getMD5(txtUserSession + Constant.SALT_STRING + DateUtility.getCurrentEpoch() + generateRandomNumber(8));
	}

	public static Long getLongFromString(String value) {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			// LoggerService.exception(UTILITY, e.getMessage(), e);
			return null;
		}
	}

	public static String generateUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/*
	 * public static String generateRandomNumberAndString(Integer length) {
	 * StringBuilder sb = new StringBuilder(); for (int i = 0; i < length; i++)
	 * sb.append(Constant.ALPHABETS.charAt(rnd.nextInt(Constant.ALPHABETS.length()))
	 * ); return sb.toString(); }
	 */

	public static String generateCookie() {
		return getMD5(generateToken(6) + "#" + DateUtility.getCurrentEpoch() + generateToken(8));
	}
	/*
	 * public static void main(String args[]) {
	 * System.out.println(getMD5(generateToken(6) + Constant.SALT_STRING +
	 * DateUtility.getDateAfterDays(DateUtility.getCurrentDate(), 365 * 10) +
	 * generateToken(8))); }
	 */

	/**
	 * This method is used to read excel file which has .xlsx format.
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<ExcelRow> readXmlFormat(File file) throws IOException {
		FileInputStream fileInputStream = null;
		List<ExcelRow> excelRowList = new ArrayList<>();
		try {
			fileInputStream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 0) {
					continue;
				}
				ExcelRow excelRow = new ExcelRow();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getColumnIndex()) {
					case 0:
						excelRow.setFirstCell(cell.toString());
						break;
					case 1:
						excelRow.setSecondCell(cell.toString());
						break;
					case 2:
						excelRow.setThirdCell(cell.toString());
						break;
					case 3:
						excelRow.setFourCell(cell.toString());
						break;
					case 4:
						excelRow.setFiveCell(cell.toString());
						break;
					case 5:
						excelRow.setSixCell(cell.toString());
						break;
					case 6:
						excelRow.setSevenCell(cell.toString());
						break;
					default:
						break;
					}
				}
				excelRowList.add(excelRow);
			}
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
		return excelRowList;
	}

	/**
	 * This method is used to read excel file which has .xls format.
	 * 
	 * @param file
	 *            Excel file
	 * @return List<{@link ExcelRow}>
	 * @throws IOException
	 */
	public static List<ExcelRow> readBinaryFormatFile(File file) throws IOException {
		FileInputStream fileInputStream = null;
		List<ExcelRow> excelRowList = new ArrayList<>();
		try {
			fileInputStream = new FileInputStream(file);

			POIFSFileSystem poifsFileSystem = new POIFSFileSystem(fileInputStream);

			HSSFWorkbook myWorkBook = new HSSFWorkbook(poifsFileSystem);
			HSSFSheet mySheet = myWorkBook.getSheetAt(0);

			Iterator rowIter = mySheet.rowIterator();

			while (rowIter.hasNext()) {
				HSSFRow myRow = (HSSFRow) rowIter.next();
				if (myRow.getRowNum() == 0) {
					continue;
				}
				Iterator cellIter = myRow.cellIterator();
				ExcelRow excelRow = new ExcelRow();
				while (cellIter.hasNext()) {
					HSSFCell myCell = (HSSFCell) cellIter.next();
					switch (myCell.getCellNum()) {
					case 0:
						excelRow.setFirstCell(myCell.toString());
						break;
					case 1:
						excelRow.setSecondCell(myCell.toString());
						break;
					default:
						break;
					}
				}
				excelRowList.add(excelRow);
			}
			return excelRowList;
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
	}

	public static List<ExcelRow> readExcelFormat(File file, String[] headers) throws HarborException {
		FileInputStream fileInputStream = null;
		List<ExcelRow> excelRowList = new ArrayList<>();
		try {
			fileInputStream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 0) {
					Iterator<Cell> cellIteratorHeader = row.cellIterator();
					int columnCount = 0;
					while (cellIteratorHeader.hasNext()) {
						Cell cell = cellIteratorHeader.next();
						columnCount = columnCount + 1;
					}
					if (columnCount != headers.length) {
						throw new HarborException(ResponseCode.INVALID_FILE_FORMAT.getCode(),
								ResponseCode.INVALID_FILE_FORMAT.getMessage());
					}
					continue;
				}
				ExcelRow excelRow = new ExcelRow();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getColumnIndex()) {
					case 0:
						excelRow.setFirstCell(cell.toString());
						break;
					case 1:
						excelRow.setSecondCell(cell.toString());
						break;
					case 2:
						excelRow.setThirdCell(cell.toString());
						break;
					case 3:
						excelRow.setFourCell(cell.toString());
						break;
					case 4:
						excelRow.setFiveCell(cell.toString());
						break;
					case 5:
						excelRow.setSixCell(cell.toString());
						break;
					case 6:
						excelRow.setSevenCell(cell.toString());
						break;
					case 7:
						excelRow.setEightCell(cell.toString());
						break;
					case 8:
						excelRow.setNineCell(cell.toString());
						break;
					case 9:
						excelRow.setTenCell(cell.toString());
						break;
					case 10:
						excelRow.setElevenCell(cell.toString());
						break;
					case 11:
						excelRow.setTwelveCell(cell.toString());
						break;
					default:
						break;
					}
				}
				excelRowList.add(excelRow);
			}
		} catch (IOException ioException) {
			LoggerService.exception(ioException);
			throw new HarborException(ResponseCode.UNABLE_TO_UPLOAD_FILE.getCode(),
					ResponseCode.UNABLE_TO_UPLOAD_FILE.getMessage());
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException ioException) {
					LoggerService.exception(ioException);
					throw new HarborException(ResponseCode.UNABLE_TO_UPLOAD_FILE.getCode(),
							ResponseCode.UNABLE_TO_UPLOAD_FILE.getMessage());
				}
			}
		}
		return excelRowList;
	}

	public static Boolean validateExcelTemplate(File file, String[] headers) throws HarborException {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 0) {
					Iterator<Cell> cellIteratorHeader = row.cellIterator();
					int columnCount = 0;
					while (cellIteratorHeader.hasNext()) {
						Cell cell = cellIteratorHeader.next();
						columnCount = columnCount + 1;
					}
					if (columnCount != headers.length) {
						throw new HarborException(ResponseCode.INVALID_FILE_FORMAT.getCode(),
								ResponseCode.INVALID_FILE_FORMAT.getMessage());
					}
					break;
				}
			}
			return true;
		} catch (IOException ioException) {
			LoggerService.exception(ioException);
			throw new HarborException(ResponseCode.UNABLE_TO_UPLOAD_FILE.getCode(),
					ResponseCode.UNABLE_TO_UPLOAD_FILE.getMessage());
		} catch (HarborException ioException) {
			LoggerService.exception(ioException);
			throw new HarborException(ResponseCode.INVALID_FILE_FORMAT.getCode(),
					ResponseCode.INVALID_FILE_FORMAT.getMessage());
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException ioException) {
					LoggerService.exception(ioException);
					throw new HarborException(ResponseCode.UNABLE_TO_UPLOAD_FILE.getCode(),
							ResponseCode.UNABLE_TO_UPLOAD_FILE.getMessage());
				}
			}
		}

	}

	public static void deleteFile(File file) {
		if (file.exists() && !file.delete()) {
			// LoggerService.info(UTILITY_CLASS, file + DELETE_STRING);
		}
	}

	/**
	 * This method is used to generate a UUID.
	 * 
	 * @return
	 */
	public static String generateUuidWithHash() {
		return UUID.randomUUID().toString();
	}

	public static boolean isValidPattern(String value, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
}
