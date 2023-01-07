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

@DisplayName("Testing invoice checking algorithm")
public class InvoicesPaymentsSmallTests {

   @Test
   @DisplayName("Slow small test for creating new invoices")
   void newInvoicesFromPaymentsSlowTest() {
      try {
         System.out.println("Starting to handle the invoices the slow way...");
         InvoiceInspector inspector = new InvoiceInspector();
         inspector.readInvoicesAndPayments("invoices.txt", "payments.txt");
         long start = System.nanoTime();
         inspector.handleInvoicesAndPaymentsSlow();
         long duration = System.nanoTime() - start;
         inspector.saveNewInvoices("to-collect-slow.txt");
         System.out.println("Handling slowly the small file took " + duration / 1000000.0 + " ms");
         assertTrue(checkFileHash("to-collect-slow.txt", "604082b91304ef06f62307783341f745753bae8c1555359d35635bfeef65371c46186392f53c9b6de3f0e62e0b27e1475ecc74494e80ab86a16a9786e5d37b71"), () -> "Wrong hash code for the file.");
      } catch (IOException e) {
         fail("Failed to manage the invoice and payments files");
      }
   }

   @Test
   @DisplayName("Faster small test for creating new invoices")
   void newInvoicesFromPaymentsTest() {
      try {
         InvoiceInspector inspector = new InvoiceInspector();
         inspector.readInvoicesAndPayments("invoices.txt", "payments.txt");
         long start = System.nanoTime();
         inspector.handleInvoicesAndPaymentsFast();
         long duration = System.nanoTime() - start;
         inspector.saveNewInvoices("to-collect.txt");
         System.out.println("Handling fast the small file took " + duration / 1000000 + " ms");
         assertTrue(checkFileHash("to-collect.txt", "604082b91304ef06f62307783341f745753bae8c1555359d35635bfeef65371c46186392f53c9b6de3f0e62e0b27e1475ecc74494e80ab86a16a9786e5d37b71"), () -> "Wrong hash code for the file.");
      } catch (IOException e1) {
         fail("Failed to manage the invoice and payments files");
      }
   }

   private boolean checkFileHash(String fileName, String correctHash) {
      try {
         // File file = new File("l-to-collect.txt");
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
         System.out.println("\nHash is " + sb.toString());
         return sb.toString().equals(correctHash);
      } catch (NoSuchAlgorithmException | IOException e) {
         System.out.println("Could not check file hash: " + e.getMessage());
      }
      return false;
   }
}
