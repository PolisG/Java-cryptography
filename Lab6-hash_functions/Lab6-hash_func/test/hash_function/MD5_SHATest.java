/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_function;

import hash_function.MD5_SHA;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Polis
 */
public class MD5_SHATest {
    
    public MD5_SHATest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class MD5_SHA.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        MD5_SHA.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHash method, of class MD5_SHA.
     */
    @Test
    public void testGetHash() {
        System.out.println("getHash");
        String input = "";
        String algorithm = "";
        String expResult = "";
        String result = MD5_SHA.getHash(input, algorithm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
