package com.mygdx.game;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
public class weapontest {
    @Test
    public void testdistancecal(){
        double t=weapon.calculateDamage(10,15,10,15,0);
        double r=0.0;
        assertFalse(t>0);

    }

}
