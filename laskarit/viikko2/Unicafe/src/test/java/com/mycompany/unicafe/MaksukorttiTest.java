package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenToimii() {
        kortti.lataaRahaa(105);
        assertEquals("saldo: 1.15", kortti.toString());
    }
    
    @Test
    public void pienetSentitTulostuvatOikein() {
        //Maksukortti-luokan toStringiin muutos tulostukseen, jotta tulostus toimii alle 10 sentin luvuilla.
        kortti.lataaRahaa(91);
        assertEquals("saldo: 1.01", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeKunRahaaTarpeeksi() {
        kortti.lataaRahaa(500);
        kortti.otaRahaa(300);
        assertEquals("saldo: 2.10", kortti.toString());
    }
    
    @Test
    public void saldoEiVaheneJosRahaaLiianVahan() {
        kortti.otaRahaa(240);
        assertEquals("saldo: 0.10", kortti.toString());
        
    }
    
    @Test
    public void riittavatkoRahatTrue() {
        kortti.lataaRahaa(500);
        assertTrue(kortti.otaRahaa(240));
        
    }
    
    @Test
    public void riittavatkoRahatFalse() {
        kortti.lataaRahaa(200);
        assertFalse(kortti.otaRahaa(240));
    }
    
    @Test
    public void riittaakoTasaraha() {
        kortti.lataaRahaa(390);
        assertTrue(kortti.otaRahaa(400));
    }
    
    @Test
    public void saldoPalautetaan() {
        assertEquals(kortti.saldo(), 10);
    }
}
