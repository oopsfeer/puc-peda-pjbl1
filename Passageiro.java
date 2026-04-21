public class Passageiro extends Vagao {
   protected int passageiros;

   public Passageiro(double comprimento, double peso, int passageiros) {
      super(comprimento, peso);
      this.passageiros = passageiros;
   }

   public int getPassageiros() {
      return passageiros;
   }

   @Override
   public void imprime() {
      System.out.println("Tipo: Passageiro");
      System.out.println("Quantidade de passageiros: " + passageiros);
      super.imprime();
   }
}