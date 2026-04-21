import java.io.*;

public class ComposicaoFerroviaria extends Deque implements Serializable {
    ObjetoPersistente ArqComp;

    public ComposicaoFerroviaria(int N, String nomeArquivo) {
        super(N);                                                               // Chama o construtor de Deque
        ArqComp = new ObjetoPersistente(nomeArquivo);
        carregar();                                                             // Carrega arquivo da composição ferroviária se existir
    }

    private void salvar() {                                                     // Salva a composição ferroviária em arquivo
        ArqComp.salvar(this);
    }

    private void carregar() {
        ComposicaoFerroviaria cf = (ComposicaoFerroviaria) ArqComp.carregar();

        if (!(cf==null)) {                                                      // Se o objeto foi recuperado, atualiza composição ferroviária
                                                                                // É necessário redefinir os atributos do "deque pai" com os dados da
                                                                                // composição carregada.
            this.front = cf.front;
            this.rear = cf.rear;
            this.ptr = cf.ptr;
            this.size = cf.size;
            this.N = cf.N;
            this.data = cf.data;
        }
    }

    public void criarComposicaoPadrao() {                                       // apaga a composição atual e monta tudo de novo do zero
        while (!isEmpty())                                                      // enquanto ainda houver vagão na composição
            deleteLast();                                                       // remove um por um pelo final

        addLast(new Locomotiva(20, 150, 2500));     // coloca a locomotiva primeiro

        for (int i = 0; i < 50; i++)                                            // adiciona os 50 vagões de passageiros
            addLast(new Passageiro(24, 40, 30));

        for (int i = 0; i < 30; i++)                                            // adiciona os 30 vagões de carga
            addLast(new Carga(17, 20));

        salvar();                                                               // salva a composição pronta no arquivo
    }

    public void inserirVagaoNoInicio(Vagao v) {                                 // adiciona um vagão na frente
        addFirst(v);
        salvar();                                                               // já salva depois de inserir
    }

    public void inserirVagaoNoFim(Vagao v) {                                    // adiciona um vagão no final
        addLast(v);
        salvar();                                                               // já salva depois de inserir
    }

    public Vagao removerVagaoDoInicio() {                                       // remove e devolve o vagão da frente
        Vagao v = (Vagao) deleteFirst();
        salvar();                                                               // salva a composição atualizada
        return v;
    }

    public Vagao removerVagaoDoFim() {                                          // remove e devolve o vagão do final
        Vagao v = (Vagao) deleteLast();
        salvar();                                                               // salva a composição atualizada
        return v;
    }

    public Vagao getPrimeiroVagao() {                                           // devolve o primeiro vagão sem remover
        return (Vagao) peekFront();
    }

    public Vagao getUltimoVagao() {                                             // devolve o último vagão sem remover
        return (Vagao) peekRear();
    }

    public int contarVagoes() {                                                 // devolve a quantidade total de vagões
        return getSize();
    }

    public int contarLocomotivas() {                                            // percorre a composição e conta quantas locomotivas existem
        int total = 0;
        rewind();                                                               // posiciona o ponteiro no começo

        for (int i = 0; i < getSize(); i++) {
            Object obj = next();                                                // pega o próximo item da composição
            if (obj instanceof Locomotiva)                                      // verifica se ele é uma locomotiva
                total++;
        }

        return total;
    }

    public int contarPassageirosVagoes() {                                      // conta quantos vagões de passageiros existem
        int total = 0;
        rewind();

        for (int i = 0; i < getSize(); i++) {
            Object obj = next();
            if (obj instanceof Passageiro)                                      // verifica se o item atual é do tipo Passageiro
                total++;
        }

        return total;
    }

    public int contarCargasVagoes() {                                           // conta quantos vagões de carga existem
        int total = 0;
        rewind();

        for (int i = 0; i < getSize(); i++) {
            Object obj = next();
            if (obj instanceof Carga)                                           // verifica se o item atual é do tipo Carga
                total++;
        }

        return total;
    }

    public double contarComprimentoTotal() {                                    // soma o comprimento de todos os vagões e os espaços entre eles
        if (isEmpty())                                                          // se estiver vazia, o comprimento total é zero
            return 0;

        double total = 0;
        rewind();

        for (int i = 0; i < getSize(); i++) {
            Vagao v = (Vagao) next();                                           // pega cada vagão da composição
            total += v.getComprimento();                                        // soma o comprimento do vagão atual
        }

        total += (getSize() - 1) * 2;                                           // soma os espaços de 2 m entre um vagão e outro
        return total;
    }

    public double contarPesoTotal() {                                           // soma o peso de todos os vagões
        double total = 0;
        rewind();

        for (int i = 0; i < getSize(); i++) {
            Vagao v = (Vagao) next();
            total += v.getPeso();
        }

        return total;
    }

    public int contarPassageirosTotal() {                                       // soma a quantidade total de passageiros transportados
        int total = 0;
        rewind();

        for (int i = 0; i < getSize(); i++) {
            Object obj = next();
            if (obj instanceof Passageiro)                                      // só soma se for vagão de passageiros
                total += ((Passageiro) obj).getPassageiros();
        }

        return total;
    }

    public double contarCargaTotal() {                                          // soma a carga total transportada
        double total = 0;
        rewind();

        for (int i = 0; i < getSize(); i++) {
            Object obj = next();
            if (obj instanceof Carga)                                           // só soma se for vagão de carga
                total += ((Carga) obj).getCarga();
        }

        return total;
    }

    public double contarPotenciaTotal() {                                       // soma a potência de todas as locomotivas
        double total = 0;
        rewind();

        for (int i = 0; i < getSize(); i++) {
            Object obj = next();
            if (obj instanceof Locomotiva)                                      // só locomotiva tem potência
                total += ((Locomotiva) obj).getPotencia();
        }

        return total;
    }

    public double calcularRelacaoPotenciaPeso() {                               // calcula quantos HP existem por tonelada
        double pesoTotal = contarPesoTotal();

        if (pesoTotal == 0)                                                     // evita divisão por zero caso a composição esteja vazia
            return 0;

        return contarPotenciaTotal() / pesoTotal;
    }

    private Locomotiva getPrimeiraLocomotiva() {                                // procura a primeira locomotiva para usar como referência
        rewind();

        for (int i = 0; i < getSize(); i++) {
            Object obj = next();
            if (obj instanceof Locomotiva)
                return (Locomotiva) obj;
        }

        return null;                                                            // retorna null se não encontrar nenhuma
    }

    public void diagnostico() {                                                 // junta todas as informações principais da composição
        int totalVagoes = contarVagoes();
        int totalLocomotivas = contarLocomotivas();
        int totalPassageiro = contarPassageirosVagoes();
        int totalCarga = contarCargasVagoes();

        double comprimentoTotal = contarComprimentoTotal();
        double pesoTotal = contarPesoTotal();
        int passageirosTotal = contarPassageirosTotal();
        double cargaTotal = contarCargaTotal();
        double potenciaTotal = contarPotenciaTotal();
        double hpt = calcularRelacaoPotenciaPeso();

        System.out.println("===== DIAGNOSTICO DA COMPOSICAO =====");
        System.out.println("Numero total de vagoes: " + totalVagoes);
        System.out.println("Locomotivas: " + totalLocomotivas);
        System.out.println("Vagoes de passageiros: " + totalPassageiro);
        System.out.println("Vagoes de carga: " + totalCarga);
        System.out.println("Comprimento total: " + comprimentoTotal + " m");
        System.out.println("Peso total: " + pesoTotal + " toneladas");
        System.out.println("Total de passageiros: " + passageirosTotal);
        System.out.println("Carga total transportada: " + cargaTotal + " toneladas");
        System.out.println("Potencia total: " + potenciaTotal + " HP");
        System.out.println("Relacao potencia/peso: " + hpt + " HP/Ton");

        double potenciaMinima = 1.05 * pesoTotal;                               // calcula a potência mínima necessária

        if (hpt >= 1.05) {
            System.out.println("Potencia suficiente para desempenho minimo");
        } else {
            double falta = potenciaMinima - potenciaTotal;                      // calcula quanto ainda falta para atingir o mínimo
            System.out.println("Potencia insuficiente para desempenho minimo");
            System.out.println("Faltam " + falta + " HP");

            Locomotiva loco = getPrimeiraLocomotiva();                          // usa uma locomotiva já existente como referência

            if (loco != null) {
                int adicionais = (int) Math.ceil(falta / loco.getPotencia());   // arredonda para cima a quantidade necessária
                System.out.println("Sugestao: adicionar " + adicionais + " locomotiva(s) igual(is) a ja existente(s)");
            } else {
                System.out.println("Nao ha locomotiva na composicao para calcular sugestao de adicao");
            }
        }
    }
}
 