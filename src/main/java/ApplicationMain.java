import model.Funcionario;
import service.FuncionarioService;

import java.util.List;

public class ApplicationMain {
    private static final int PORCENTAGEM_AUMENTO = 10;
    private static final double SALARIO_MINIMO = 1220.00;

    public static void main(String[] args) {

        FuncionarioService funcionarioService = new FuncionarioService();

        System.out.println("3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.:");
        List<Funcionario> funcionarios = funcionarioService.funcionariosFactory();
        System.out.println("\n");

        System.out.println("3.2 – Remover o funcionário “João” da lista.");
        funcionarioService.removerJoao(funcionarios);

        System.out.println("3.3 – Imprimir todos os funcionários com todas suas informações, sendo que: \n" +
              "• informação de data deve ser exibido no formato dd/mm/aaaa \n" +
              "• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.");
        funcionarioService.imprimirFuncionarios(funcionarios);
        System.out.println("\n");

//        "3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor."
        funcionarioService.receberAumento(PORCENTAGEM_AUMENTO, funcionarios);

//        3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        funcionarioService.map(funcionarios);

        System.out.println("3.6 – Imprimir os funcionários, agrupados por função:.");
        funcionarioService.imprimirMap(funcionarioService.map(funcionarios));
        System.out.println("\n");

        System.out.println("3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.");
        funcionarioService.imprimirFuncionariosDosMeses10e02(funcionarios);
        System.out.println("\n");

        System.out.println("3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.");
        funcionarioService.imprimirMaiorIdade(funcionarios);
        System.out.println("\n");

        System.out.println("3.10 – Imprimir a lista de funcionários por ordem alfabética.");
        funcionarioService.imprimirListaOrdenadaPorNome(funcionarios);
        System.out.println("\n");

        System.out.println("3.11 – Imprimir o total dos salários dos funcionários.");
        funcionarioService.totalDosSalarios(funcionarios);
        System.out.println("\n");

        System.out.println("3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.");
        funcionarioService.quantidadeDeSalariosMinimosPorFuncionario(SALARIO_MINIMO, funcionarios);

    }
}

