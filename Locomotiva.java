public class Locomotiva extends Vagao {
   protected double potencia;                                               // Potência da locomotiva em HP

   public Locomotiva(double comprimento, double peso, double potencia) {
      super(comprimento, peso);
      this.potencia = potencia;
   }

   public double getPotencia() {
      return potencia;
   }

   @Override
   public void imprime() {
      System.out.println("Tipo: Locomotiva");
      System.out.println("Potencia: " + potencia + " HP");
      super.imprime();
   }
}
