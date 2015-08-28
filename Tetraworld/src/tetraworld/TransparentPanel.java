 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import javax.swing.JPanel;

/** Classe servant à créer un JPanel transparent
 *
 * @author Romain SANFILIPPO
 */
public class TransparentPanel extends JPanel{
    
    /**
     * Constructeur par défaut, qui crée un panel et le rend transparent
     */
    public TransparentPanel(){
        super();
        this.setOpaque(false);
    }
    
}
