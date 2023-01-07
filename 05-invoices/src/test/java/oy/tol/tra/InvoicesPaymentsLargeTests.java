package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Testing invoice checking algorigthm")
public class InvoicesPaymentsLargeTests {

   @Test
   void newInvoicesFromPaymentsSlowTest() {
      try {
         System.out.println("Starting to handle the invoices the slow way...");
         InvoiceInspector inspector = new InvoiceInspector();
         inspector.readInvoicesAndPayments("l-invoices.txt", "l-payments.txt");
         long start = System.nanoTime();
         inspector.handleInvoicesAndPaymentsSlow();
         long duration = System.nanoTime() - start;
         inspector.saveNewInvoices("l-to-collect-slow.txt");
         System.out.println("Handling the large file took " + duration / 1000000.0 + " ms");
         assertTrue(checkFileHash("l-to-collect-slow.txt", "bcf5b1db6341098985a694eddaf360aeb54f973c5b31b091c23817d9f51865cef90c1e3d2cadeedde30661f4e043e03b7b9008ea62dd27a68e2e34239b2d26f6"), () -> "Wrong hash code for the file.");
      } catch (IOException e) {
         fail("Failed to manage the invoice and payments files");
      }
   }

   @Test
   void newInvoicesFromPaymentsFastTest() {
      try {
         System.out.println("Starting to handle the invoices the faster way...");
         InvoiceInspector inspector = new InvoiceInspector();
         inspector.readInvoicesAndPayments("l-invoices.txt", "l-payments.txt");
         long start = System.nanoTime();
         inspector.handleInvoicesAndPaymentsFast();
         long duration = System.nanoTime() - start;
         inspector.saveNewInvoices("l-to-collect.txt");
         System.out.println("Handling the large file took " + duration / 1000000 + " ms");
         assertTrue(checkFileHash("l-to-collect.txt", "bcf5b1db6341098985a694eddaf360aeb54f973c5b31b091c23817d9f51865cef90c1e3d2cadeedde30661f4e043e03b7b9008ea62dd27a68e2e34239b2d26f6"), () -> "Wrong hash code for the file.");
      } catch (IOException e) {
         fail("Failed to manage the invoice and payments files");
      }
   }

   private boolean checkFileHash(String fileName, String correctHash) {
      try {
         File file = new File(fileName);
         MessageDigest md = MessageDigest.getInstance("SHA-512");
         FileInputStream fis = new FileInputStream(file);
         byte[] dataBytes = new byte[1024];
   
         int nread = 0;
         while ((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
         }
         byte[] mdbytes = md.digest();
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
         }
         fis.close();
         String calculatedHash = sb.toString();
         System.out.println("Calculated hash: " + sb.toString());
         boolean matches = calculatedHash.equals(correctHash);
         if (!matches) {
            System.out.println("Correct hash   : " + sb.toString());
         }
         return matches;
      } catch (NoSuchAlgorithmException | IOException e) {
         System.out.println("Could not check file hash: " + e.getMessage());
      }
      return false;
   }
}
