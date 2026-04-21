import java.util.Scanner;

public class ProgramaFerrovia {

    public static int lerInt(Scanner entrada, String msg) {                     // mostra a mensagem e lê um número inteiro
        System.out.print(msg);
        return Integer.parseInt(entrada.nextLine());                            // usa nextLine para não dar problema com quebra de linha
    }

    public static double lerDouble(Scanner entrada, String msg) {               // mostra a mensagem e lê um número com casas decimais
        System.out.print(msg);
        return Double.parseDouble(entrada.nextLine());                          // também usa nextLine pelo mesmo motivo
    }

    public static Vagao criarVagao(Scanner entrada) {                           // monta e devolve o vagão escolhido pela pessoa
        String tipo;

        System.out.println("\nTipos de vagao");
        System.out.println("1 - Locomotiva");
        System.out.println("2 - Passageiro");
        System.out.println("3 - Carga");
        System.out.print("Escolha o tipo: ");
        tipo = entrada.nextLine();

        if (tipo.equals("1")) {                                                 // se escolheu locomotiva, pede os dados dela
            double comprimento = lerDouble(entrada, "Comprimento da locomotiva: ");
            double peso = lerDouble(entrada, "Peso da locomotiva: ");
            double potencia = lerDouble(entrada, "Potencia da locomotiva: ");
            return new Locomotiva(comprimento, peso, potencia);
        }

        if (tipo.equals("2")) {                                                 // se escolheu passageiro, pede os dados desse tipo
            double comprimento = lerDouble(entrada, "Comprimento do vagao de passageiros: ");
            double peso = lerDouble(entrada, "Peso do vagao de passageiros: ");
            int passageiros = lerInt(entrada, "Quantidade de passageiros: ");
            return new Passageiro(comprimento, peso, passageiros);
        }

        if (tipo.equals("3")) {                                                 // se escolheu carga, pede só comprimento e peso
            double comprimento = lerDouble(entrada, "Comprimento do vagao de carga: ");
            double peso = lerDouble(entrada, "Peso total do vagao de carga: ");
            return new Carga(comprimento, peso);
        }

        System.out.println("Tipo invalido");                                    // cai aqui se a opção digitada não existir
        return null;
    }

    public static void mostrarVagao(Vagao v) {                                  // mostra os dados do vagão recebido
        if (v == null) {                                                        // se vier null, significa que não tem vagão para mostrar
            System.out.println("Nao existe vagao na composicao");
        } else {
            v.imprime();                                                        // usa o imprime da própria classe do objeto
        }
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int tamanho;
        String opcao;

        do {                                                                    // repete até a pessoa informar um tamanho que comporte a composição padrão
            tamanho = lerInt(entrada, "Informe o tamanho da composicao: ");
            if (tamanho < 81)
                System.out.println("Use um tamanho de pelo menos 81");
        } while (tamanho < 81);

        ComposicaoFerroviaria cf = new ComposicaoFerroviaria(tamanho, "composicao.dat");
                                                                                // cria a composição e tenta carregar o arquivo se ele já existir

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("a - criar composicao padrao");
            System.out.println("b - inserir vagao");
            System.out.println("c - remover vagao");
            System.out.println("d - apresentar descricao da composicao");
            System.out.println("e - apresentar dados do primeiro vagao");
            System.out.println("f - apresentar dados do ultimo vagao");
            System.out.println("g - terminar");
            System.out.print("Opcao: ");
            opcao = entrada.nextLine().toLowerCase();                           // lê a opção e já transforma em minúscula

            switch (opcao) {
                case "a": {
                    cf.criarComposicaoPadrao();                                 // apaga a composição atual e monta a padrão
                    System.out.println("Composicao padrao criada com sucesso");
                    break;
                }

                case "b": {
                    if (cf.isFull()) {                                          // evita tentar inserir quando não cabe mais nada
                        System.out.println("A composicao ja esta cheia");
                        break;
                    }

                    Vagao v = criarVagao(entrada);                              // cria o vagão com os dados digitados

                    if (v != null) {                                            // só continua se o tipo escolhido foi válido
                        System.out.print("Inserir no inicio ou no fim? ");
                        String lado = entrada.nextLine().toLowerCase();

                        if (lado.equals("inicio")) {
                            cf.inserirVagaoNoInicio(v);                         // coloca o vagão na frente
                            System.out.println("Vagao inserido no inicio");
                        } else if (lado.equals("fim")) {
                            cf.inserirVagaoNoFim(v);                            // coloca o vagão no final
                            System.out.println("Vagao inserido no fim");
                        } else {
                            System.out.println("Opcao invalida");
                        }
                    }
                    break;
                }

                case "c": {
                    if (cf.isEmpty()) {                                         // evita tentar remover quando a composição está vazia
                        System.out.println("A composicao esta vazia");
                        break;
                    }

                    System.out.print("Remover do inicio ou do fim? ");
                    String lado = entrada.nextLine().toLowerCase();
                    Vagao removido = null;                                      // começa como null e depois recebe o vagão removido

                    if (lado.equals("inicio")) {
                        removido = cf.removerVagaoDoInicio();                   // remove pela frente
                        System.out.println("Vagao removido do inicio");
                    } else if (lado.equals("fim")) {
                        removido = cf.removerVagaoDoFim();                      // remove pelo final
                        System.out.println("Vagao removido do fim");
                    } else {
                        System.out.println("Opcao invalida");
                    }

                    if (removido != null) {                                     // se realmente removeu, mostra qual foi o vagão
                        System.out.println("Dados do vagao removido");
                        removido.imprime();
                    }
                    break;
                }

                case "d": {
                    System.out.println("\nComposicao atual");
                    System.out.println(cf);                                     // mostra os tipos de vagão na ordem em que estão
                    cf.diagnostico();                                           // mostra todos os cálculos e informações principais
                    break;
                }

                case "e": {
                    System.out.println("\nPrimeiro vagao");
                    mostrarVagao(cf.getPrimeiroVagao());                        // pega o primeiro e imprime
                    break;
                }

                case "f": {
                    System.out.println("\nUltimo vagao");
                    mostrarVagao(cf.getUltimoVagao());                          // pega o último e imprime
                    break;
                }

                case "g": {
                    System.out.println("Programa encerrado");
                    break;
                }

                default: {
                    System.out.println("Opcao invalida");
                }
            }

        } while (!opcao.equals("g"));                                  // continua rodando até escolher sair

        entrada.close();                                                        // fecha o scanner no final
    }
}
