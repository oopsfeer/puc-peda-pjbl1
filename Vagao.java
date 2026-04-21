import java.io.Serializable;

public abstract class Vagao implements Serializable {
   protected double comprimento;                            // Comprimento do vagão em metros
   protected double peso;                                   // Peso do vagão em toneladas

   public Vagao(double comprimento, double peso) {
      this.comprimento = comprimento;
      this.peso = peso;
   }

   public double getComprimento() {
      return comprimento;
   }

   public double getPeso() {
      return peso;
   }

   public void imprime() {
      System.out.println("Comprimento: " + comprimento + " m");
      System.out.println("Peso: " + peso + " toneladas");
   }

   @Override
   public String toString() {
      return getClass().getSimpleName();
   }
}