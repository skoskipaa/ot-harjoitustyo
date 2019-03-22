package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(0);

    }

    @Test
    public void kassapaateOlemassa() {
        assertTrue(paate != null);
    }

    @Test
    public void kassassaAluksiRahaa() {
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void myytyjaEdullisiaLounaitaAluksiNolla() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());

    }

    @Test
    public void myytyjaMaukkaitaLounaitaAluksiNolla() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKateisellaRahatEivatRiita() {
        paate.syoEdullisesti(200);
        paate.syoEdullisesti(100);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKateisellaVaihtorahalla() {
        paate.syoEdullisesti(300);
        paate.syoEdullisesti(300);
        assertEquals(100480, paate.kassassaRahaa());
        assertEquals(2, paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void syoMaukkaastiKateisellaRahatEivatRiita() {
        paate.syoMaukkaasti(300);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void syoMaukkaastiKateisellaVaihtorahalla() {
        paate.syoMaukkaasti(500);
        assertEquals(100400, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKateisella() {
        for (int i = 0; i < 10; i++) {
            paate.syoEdullisesti(240);
        }
        assertEquals(102400, paate.kassassaRahaa());
        assertEquals(10, paate.edullisiaLounaitaMyyty());

    }

    @Test
    public void syoMaukkaastiKateisella() {
        for (int i = 0; i < 10; i++) {
            paate.syoMaukkaasti(400);
        }
        assertEquals(104000, paate.kassassaRahaa());
        assertEquals(10, paate.maukkaitaLounaitaMyyty());

    }
    @Test
    public void syoEdullisestiJosKortillaEiRahaa() {
        assertFalse(paate.syoEdullisesti(kortti));
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
     @Test
    public void syoMaukkaastiJosKortillaEiRahaa() {
        assertFalse(paate.syoMaukkaasti(kortti));
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void lataaRahaaKortilleKassanSummaMuuttuu() {
        paate.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, paate.kassassaRahaa());
        assertEquals(1000, kortti.saldo());
    }
    @Test
    public void lataaRahaaNegatiivinenEiKay() {
        paate.lataaRahaaKortille(kortti, -500);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, kortti.saldo());
    }
    @Test
    public void syoEdullisestiKortillaJosRahaa() {
        paate.lataaRahaaKortille(kortti, 1000);
        assertTrue(paate.syoEdullisesti(kortti));
        // Kassaan rahaa, koska ladattu korttia!
        assertEquals(101000, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void syoMaukkaastiKortillaJosRahaa() {
        paate.lataaRahaaKortille(kortti, 1000);
        assertTrue(paate.syoMaukkaasti(kortti));
        // Kassaan rahaa, koska ladattu korttia!
        assertEquals(101000, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }

}
