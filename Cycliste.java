import java.util.Scanner;

class Cycliste {
       private static Scanner clavier = new Scanner(System.in);
       public static void main(String[] args) {

        double t = 0.0;    // temps, en min.
        double d = 0.0;    // distance, en km
        double v = 30.0;   // vitesse, en km/h
        double acc = 0.0;  // accélération, en km/h/min
        double p = 175.0;  // puissance, en W

        /******************************************
         * Completez le programme a partir d'ici.
         *******************************************/

        //declare les variables a entrer par l utilisateur
        double masseCycliste = 0.0;
        double vent = 0.0;
        double dtot = 0.0;      //Distance du parcourt
        double lmontee = 0.0;   //Longueur de la monte en km
        double pmontee = 0.0;   //Pente en % de la montee
        double pdescente= 0.0;  //Pente en % de la descente
        double dt = 1.0/60;        //Pas de temps
        double pmin = 10.0;      //Puissance minimale que le cycliste fournit
       
                
        //demande les valeurs a l utilisateur - Poid du cycliste
        do {
        	System.out.print("masse du cycliste (entre 40 et 180 ) ? ");
            masseCycliste = clavier.nextDouble();
        }     
              while ( masseCycliste < 40 || masseCycliste > 180);
        
       //demande la valeur a l utilisateur - Vitesse du vent
        do {
        	System.out.print("vent (entre -20 et +20 km/h) ? ");
            vent = clavier.nextDouble();
         }     
              while ( !(vent >= - 20) || !(vent <= 20) );
        
       //demande la valeur a l utilisateur - Distance totale du parcourt du cycliste
        do {
           System.out.print("distance du parcours (<= 200 km) ? ");
           dtot = clavier.nextDouble();
         }
              while ( !(dtot >= 11) || !(dtot <= 200) );
        
        //demande la valeur a l utilisateur - Distance de parcourt du cycliste au Sommet du col (lmonte)
        //La longueur en Km. Doit etre positive et telle qu il reste au moins 10 Km de descente
        // (<= 40.0 km)
        do {
         	System.out.print("distance au sommet du col " + "(<=" +(dtot - 10) );
         	System.out.print(" km) ? ");
        	lmontee = clavier.nextDouble();
           }
              while ( (lmontee <= 0) || !(lmontee <= (dtot - 10)) );
       
        
        //Demande la valeur a l utilisateur - La pente en % de la montee
        do {
           System.out.print("pente moyenne jusqu'au sommet (<= 20 %) ? ");
           pmontee = clavier.nextDouble();
        }
             while ( !(pmontee >  0) || !( pmontee <= 20));
           
        //Demande la valeur a l utilisateur - La pente en % de la descente
        do {
        	System.out.print("pente moyenne après le sommet (<= 20 %) ? ");
        	pdescente = clavier.nextDouble();
           }
             while ( !(pdescente > 0) || !(pdescente <= 20));
        

        //Imprime la premiere ligne de valeurs alors que les compteurs sont encore a zero
        
        System.out.format("%.0f, %.2f, %.2f, %.4f,  %.2f\n",
                t, d, v, acc, p);
   
        ////////////SIMULATION COMMENCEMENT////////////////////////
        
        //Variables temps et distance qui changent
        double t1 = 0;
        double d1 = 0; 
        
        do {
        	
        	
            //Calculer la vitesse du vent 
              double ve = v - vent;
                      	
         //Calcul de la puissance 
            if (lmontee > 0 && p > pmin) {
           	    p = ( p - 0.5 * dt);
           	   }
              
         
            
          //Calculer l'acceleration
            
             acc = (-2118.96*Math.sin(Math.atan(pmontee/100)))-((5*ve*(Math.abs(ve)/masseCycliste)));
             if     ( p > 0 && v > 0 ) {
    	            acc = ( acc + (777.6 * (p / ( v * masseCycliste))));
             }
            
             { if     (Math.abs(acc) < 1e-5) {
    	               acc = 0;
             }     
    	 
                   v = (v + acc * dt);}
             
             //Bernard abandonne
             if (v < 3) {
          	   System.out.println("## Bernard abandonne, il n'en peut plus");
          	   System.out.format("%.0f, %.2f, %.2f, %.4f, %.2f\n",
                 t, d, v, acc, p); 
    			   return;
    			   }
       
           //Calcul du temps d'un pas de temps
             t1 = t + dt;
             t = t1; 
             
                        
            //Calculer la distance
             d1 = d + (v * dt/60);
             d = d1;
                  
           
           double roundedTime = Math.round(t);	
        	  if (Math.abs(roundedTime - t) < 1e-5 && (int)roundedTime % 5 == 0) {
               System.out.format("%.0f, %.2f, %.2f, %.4f,  %.2f\n",
                                      t, d, v, acc, p); } 
                 } while (lmontee >= d1);		
                 System.out.format("## Bernard a atteint le sommet en %.0f min.\n", t);

                
           
           
          // Descente de Bernard
           
           do {   
          	 p = pmin;    	 
   
          // Temps
   	      t1 = (t+dt);
      	  t = t1;	
      	  
            
         // Vitesse du vent
           double ve2 = v-vent; 
     
         // Vitesse accélaration :
         	acc = (-2118.96*Math.sin(Math.atan(-pdescente/100)))-((5*ve2*(Math.abs(ve2)/masseCycliste)));
      
           		if (p>0 && v>0) {acc = acc+(777.6*(p/(v*masseCycliste)));}        		
   
           		    { if (Math.abs(acc)<1e-5) {
           		    	 acc= 0;
           		    }
   
             v = v + acc*dt;
         	 }	
           
         	// Distance	
               d1 = d+v*(dt/60); 
               d=d1;
           		    
           double roundedTime = Math.round(t);	
           if (Math.abs(roundedTime - t) < 1e-5 && (int)roundedTime % 5 == 0) {
           			System.out.format("%.0f, %.2f, %.2f, %.4f,  %.2f\n",
                                 t, d, v, acc, p);
           	}
           
            } while ((dtot)>=d1);
           
        
        
   
        /*******************************************
         * Ne rien modifier apres cette ligne.
         *******************************************/

        System.out.println("## Bernard est arrivé");
        System.out.format("%.0f, %.2f, %.2f, %.4f, %.2f\n",
                          t, d, v, acc, p);
    }
}
