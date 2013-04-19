package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;

public class ToolsFTP {


	public static boolean sendToServer (String url,String ftpServerAddress,String pathDirServer,String userName,String password){
		FTPClient client = new FTPClient();
		FileInputStream fis = null;
		boolean result = true;

		try {
			client.connect(ftpServerAddress);
			result = client.login(userName, password);

			if (result == true) {
				System.out.println("Successfully logged in!");
			} else {
				System.out.println("Login Fail!");
				return false;
			}
			client.setFileType(FTP.BINARY_FILE_TYPE);

			client.changeWorkingDirectory(pathDirServer);


			File file = new File(url);
			String testName = file.getName(); 
			fis = new FileInputStream(file);

			// Upload file to the ftp server
			result = client.storeFile(testName, fis);

			if (result == true) {
				System.out.println("File is uploaded successfully");
			} else {
				System.out.println("File uploading failed");
			}
			client.logout();
		} catch (FTPConnectionClosedException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				client.disconnect();
			} catch (FTPConnectionClosedException e) {
				System.out.println(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
