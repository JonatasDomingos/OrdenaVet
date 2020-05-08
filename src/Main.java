import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner s = new Scanner(System.in); // 1
    private static Integer[] dados; // 1
    private static int teste; // 1
    private static String num; // 1

    public static void troca(int i, int j, Comparable[]a){  // 1
        Comparable temp = a[i]; // 1
        a[i] = a[j]; // 1
        a[j] = temp; // 1
    } // assistente da mágica

    // (8)

    public static double ordenacao(Integer [ ] a ) { // 1
        int i, j, min; // 1
        for (i = 0; i < a.length; i++) { // n
            min = i; // n-1
            for (j = i + 1; j < a.length; j++) { // n-1
                if (a[j].compareTo(a[min]) <= 0) { // n-1
                    min = j; // n-1
                }
            }
            troca(min, i, a); // n-1
        }
        return 0; // 1
    } // mágica bolada

    // 2 + ( n + (n-1) + ( (n-1) * (n-1) * (n-1) ) + (n-1) + 1
    // 2 + ( n + (n-1) + ( (n^2 - 2n + 1) * (n-1) ) + (n-1) + 1
    // 2 + ( n + (n-1) + ( n^3 - n^2 - 2n^2 + 2n + n - 1 ) + (n-1) + 1
    // 2 + ( n + (n-1) + (

    private static Integer[] pegaEntrada(){
        dados = new Integer[(teste)];
        for (int i = 0; i<dados.length; i++){
            dados[i] = s.nextInt();
        }
        return dados;
    } // Pega a entrada do usuário e cria o tamanho do vetor manualmente.

    private static void imprimirVetor(Integer[] arr){
        for (int i=0; i< arr.length; i++) {
            System.out.print(arr[i] + " ");
        }System.out.println();
    } // já fala por si só...

    private static Double aleatorio1(Integer tamanho){
        long tempoInicial = System.currentTimeMillis();
        Random gerar = new Random();        Integer[] dados = new Integer[tamanho]; //cria vetor de Xmil posições
        for(int i = 0; i < dados.length; i++) {
            int rnd = (int) (Math.random() * 1000); // gera um valor randomico de 0.0 ate menores q 1.0, multiplica por 100. Convertendo o resultado para int.
            dados[i] = rnd; // atribui o valor a cada indice do vetor
        }
        System.out.println("O tamanho do vetor gerado foi de '"+dados.length+"' e seus valores são: ");
        imprimirVetor(dados);
        ordenacao(dados);
        long tempoFinal = System.currentTimeMillis();
        double tempo = (tempoFinal-tempoInicial);
        System.out.println("O vetor de " +tamanho+ " está sendo ordenado ");
//        imprimirVetor(dados);
        System.out.println();
        System.out.println("Tempo total da execução do processo foi de: "+(tempoFinal-tempoInicial)/1000d);
        System.out.println();

        return tempo;
    } //executa automática a parada toda.


    private static void manual(){
        System.out.println("insira " + teste + " números aleatórios para a ordenação crescente.");
        pegaEntrada();
        long tempoInicial = System.currentTimeMillis();
        System.out.println("Valores Informados: ");
        imprimirVetor(dados);
        ordenacao(dados);
        long tempoFinal = System.currentTimeMillis();
        System.out.println("Valores Ordenados ");
        imprimirVetor(dados);
        System.out.println();
        System.out.println("Tempo total da execução do processo foi de: "+(tempoFinal-tempoInicial)/1000d);

    } // Executa depois de colocar os valores tudo na mão.

    private static void escolha(){
        System.out.println("Digite '0' para definir automático um vetor de tamanho 10mil, 50mil, 100mil, 150mil e 200mil e seus valores:");
        System.out.println("Ou informe o seu tamanho e valores. ");
        teste = s.nextInt();
        if (teste==0) {

        double[][] dataPlot = {{10000, aleatorio1(10000)}, {50000, aleatorio1(50000)}, {80000, aleatorio1(80000)}, {100000, aleatorio1(100000)}, {120000, aleatorio1(120000)}};
        plotarGrafico(dataPlot);


        } else {
            manual();

        }
        System.out.println("Deseja executar novamente? 1-SIM 2-NAO");
        int num = s.nextInt();
        if (num==1){
            escolha();
        }else
            System.out.println("Até a próxima");


    } //loopzinho básico

    private static void plotarGrafico(double[][] dataPlot) {

        JavaPlot p = new JavaPlot();

        p.setTitle("Gráfico para Apresentação do Tempo de Execução do Algoritmo de Ordenação");
        p.getAxis("x").setLabel("Número de Entradas");
        p.getAxis("y").setLabel("Tempo de Execução (ms)");

        PlotStyle plotStyle = new PlotStyle();
        plotStyle.setStyle(Style.LINESPOINTS);
        plotStyle.setLineType(3);
        plotStyle.setPointType(7);

        DataSetPlot dataSet = new DataSetPlot(dataPlot);
        dataSet.setPlotStyle(plotStyle);
        dataSet.setTitle("Randômicos");

        p.addPlot(dataSet);
        p.plot();
    }


    public static void main(String[] args) {
        System.out.println("*******Ordenação de vetores numéricos:*********");
        escolha();

    }
}
