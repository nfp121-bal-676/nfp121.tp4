package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        // donnee.addActionListener(null /* null est � remplacer */);
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);  push.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                   Controleur.this.push(); }});
        boutons.add(add);   add.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                   Controleur.this.add(); }}); 
        boutons.add(sub);   sub.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                   Controleur.this.subtract(); }});
        boutons.add(mul);   mul.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                   Controleur.this.multiply(); }});
        boutons.add(div);   div.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                   Controleur.this.divide(); }});
        boutons.add(clear); clear.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                   Controleur.this.clear(); }});
        add(boutons);
        boutons.setBackground(Color.red);
        refreshUI();
    }

    public void refreshUI() {
        // à compléter
        if(pile.estVide()){
          add.setEnabled(false);
          sub.setEnabled(false);
          mul.setEnabled(false);
          div.setEnabled(false);
          clear.setEnabled(false);
          push.setEnabled(true);
       }
       else if(pile.taille()== 1){
          add.setEnabled(false);
          sub.setEnabled(false);
          mul.setEnabled(false);
          div.setEnabled(false);
          clear.setEnabled(true);
          push.setEnabled(true);
        }
        else if(pile.taille()> 1){
          add.setEnabled(true);
          sub.setEnabled(true);
          mul.setEnabled(true);
          div.setEnabled(true);
          clear.setEnabled(true);
          push.setEnabled(true);
        }
        else if(pile.estPleine()) {
          push.setEnabled(false);
          add.setEnabled(true);
          sub.setEnabled(true);
          mul.setEnabled(true);
          div.setEnabled(true);
          clear.setEnabled(true);
        }
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }

    // à compléter
    // en cas d'exception comme division par zéro, 
    // mauvais format de nombre suite à l'appel de la méthode operande
    // la pile reste en l'état (intacte)
    public void push(){
        try{
            this.pile.empiler(operande());
        }catch(Exception e){

        }
        this.refreshUI();
    }
    
    public void add(){
        try{
            this.pile.empiler(this.pile.depiler() + this.pile.depiler());
        }catch(Exception e){
        }
        this.refreshUI();
        
    }
    
    public void subtract(){
        try{
            int o = this.pile.sommet();
            this.pile.depiler();
            this.pile.empiler(this.pile.depiler() - o);
            
        }catch(Exception e){
        }
        this.refreshUI();
    }
    
    public void multiply(){
        try{
            this.pile.empiler(this.pile.depiler() * this.pile.depiler());
        }catch(Exception e){
        }
        this.refreshUI();
    }
    
    public void divide(){
        try{
            int o = this.pile.sommet();
            if(o != 0){
                this.pile.depiler();
                this.pile.empiler(this.pile.depiler() / o);
            }
            if(o == 0){
                //on ne fait rien
            }
        }catch(Exception e){
        }
        this.refreshUI();
    }
    
    public void clear(){
        for(int i = this.pile.taille(); i >=0 ; i--){
            try{
                this.pile.depiler();
            }catch(Exception e){
            }
        }
        this.refreshUI();
    }
}
