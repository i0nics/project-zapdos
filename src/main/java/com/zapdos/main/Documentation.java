package com.zapdos.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Documentation {
   public Documentation() {
      createWindow();
   }

   public void createWindow() {    
      JFrame frame = new JFrame("DIGI 2D Game Engine API");
      //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      createUI(frame);
      frame.setSize(1000, 900);      
      frame.setLocationRelativeTo(null);  
      frame.setVisible(true);
   }

   public void createUI(final JFrame frame){  
      JPanel panel = new JPanel();
      LayoutManager layout = new FlowLayout();  
      panel.setLayout(layout);       

      JEditorPane jEditorPane = new JEditorPane();
      jEditorPane.setEditable(false);   
      URL url= Documentation.class.getResource("index.html");

      try {   
         jEditorPane.setPage(url);
      } catch (IOException e) { 
         jEditorPane.setContentType("text/html");
         jEditorPane.setText("<html>Page not found.</html>");
      }

      JScrollPane jScrollPane = new JScrollPane(jEditorPane);
      jScrollPane.setPreferredSize(new Dimension(900,800));      

      panel.add(jScrollPane);
      frame.getContentPane().add(panel, BorderLayout.CENTER);    
   }  
} 