package service;

import model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuncionarioService {

    public Funcionario inserirFuncionario(String nome, String dataNascimento, Double salario, String funcao) {
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(nome);
        novoFuncionario.setDataNascimento(dataNascimento);
        novoFuncionario.setSalario(toBigDecimal(salario));
        novoFuncionario.setFuncao(funcao);

        return novoFuncionario;
    }

    public void removerJoao(List<Funcionario> listaDeFuncionarios) {
        for (int i = 0; i < listaDeFuncionarios.size(); i++) {
            if (listaDeFuncionarios.get(i).getNome() == "João") {
                System.out.println("Removido o funcionario: "+listaDeFuncionarios.get(i).getNome()+"\n");
                listaDeFuncionarios.remove(i);
            }
        }
    }

    public void imprimirFuncionarios(List<Funcionario> listaDeFuncionarios) {
        for (Funcionario funcionario : listaDeFuncionarios) {
            System.out.println("Nome: " + funcionario.getNome()
                    + " - Data de Nascimento: " + funcionario.getDataNascimentoToString()
                    + " - Salario: " + formatarValor(funcionario.getSalario())
                    + " - Função: " + funcionario.getFuncao());
        }
    }

    public void receberAumento(double porcentagem, List<Funcionario> listaDeFuncionarios) {
        porcentagem = (porcentagem / 100);
        for (int i = 0; i < listaDeFuncionarios.size(); i++) {
            Double salarioAtual = listaDeFuncionarios.get(i).getSalario().doubleValue();
            double novoSalario = salarioAtual + (salarioAtual * porcentagem);
            listaDeFuncionarios.get(i).setSalario(toBigDecimal(novoSalario));
        }
    }

    public Map<String, List<Funcionario>> map(List<Funcionario> funcionarioList) {
        Map<String, List<Funcionario>> map = new HashMap<>();
        for (int i = 0; i < funcionarioList.size(); i++) {
            if (!map.containsKey(funcionarioList.get(i).getFuncao())) {
                List<Funcionario> list = new ArrayList<>();
                list.add(funcionarioList.get(i));
                map.put(funcionarioList.get(i).getFuncao(), list);
            } else {
                map.get(funcionarioList.get(i).getFuncao()).add(funcionarioList.get(i));
            }
        }
        return map;
    }

    public void imprimirMap(Map<String, List<Funcionario>> listMap) {
        listMap.forEach((k, v) -> {
            for (int i = 0; i < v.size(); i++) {
                System.out.println(k + " = " + v.get(i).getNome() + " - "
                        + v.get(i).getDataNascimentoToString() + " - "
                        + formatarValor(v.get(i).getSalario()));
            }
            System.out.println("\n");
        });
    }

    public void imprimirFuncionariosDosMeses10e02(List<Funcionario> funcionarios) {
        List<Funcionario> funcionariosResponse = new ArrayList<>();
        for (int i = 0; i < funcionarios.size(); i++) {
            LocalDate dataNascimento = funcionarios.get(i).getDataNascimento();
            if (dataNascimento.getMonth().getValue() == 2 || dataNascimento.getMonth().getValue() == 10) {
                funcionariosResponse.add(funcionarios.get(i));
            }
        }
        imprimirFuncionarios(funcionariosResponse);
    }

    public void imprimirMaiorIdade(List<Funcionario> funcionarios) {
        int anoAtual = LocalDate.now().getYear();
        int idade = 0;
        String nome = "";
        for (int i = 0; i < funcionarios.size(); i++) {
            int year = funcionarios.get(i).getDataNascimento().getYear();
            if ((anoAtual - year) > idade) {
                idade = (anoAtual - year);
                nome = funcionarios.get(i).getNome();
            }
        }
        System.out.println("Funcionário mais velho: Nome: " + nome + " - Idade: " + idade);
    }

    public void imprimirListaOrdenadaPorNome(List<Funcionario> funcionarios) {
        funcionarios.sort((f1, f2) -> {
            String aux = f1.getNome();
            return aux.compareTo(f2.getNome());
        });
        imprimirFuncionarios(funcionarios);
    }

    public void totalDosSalarios(List<Funcionario> funcionarios) {
        Double salarioTotal = 0D;
        for (int i = 0; i < funcionarios.size(); i++) {
            salarioTotal = salarioTotal + funcionarios.get(i).getSalario().doubleValue();
        }
        System.out.println("Soma de todos os salários: " + formatarValor(toBigDecimal(salarioTotal)));
    }

    public void quantidadeDeSalariosMinimosPorFuncionario(Double salarioMinimo, List<Funcionario> funcionarios) {
        Double quantidadeDeSalarioMinimo = 0D;
        for (int i = 0; i < funcionarios.size(); i++) {
            Double salario = funcionarios.get(i).getSalario().doubleValue();
            quantidadeDeSalarioMinimo = salario / salarioMinimo;
            System.out.println("Nome: " + funcionarios.get(i).getNome()
                    + " - Salario: " + formatarValor(funcionarios.get(i).getSalario())
                    + " - Quantidade de Salarios mínimo: " + quantidadeDeSalarioMinimo.floatValue());
        }
    }

    public String formatarValor(BigDecimal salario) {
        String valorFormatado = NumberFormat.getCurrencyInstance().format(salario);
        return valorFormatado;
    }

    public BigDecimal toBigDecimal(Double salario) {
        return new BigDecimal(salario).setScale(2, RoundingMode.HALF_EVEN);
    }

    public List<Funcionario> funcionariosFactory() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(inserirFuncionario("Marla", "18/10/2000", 2009.44, "Operador"));
        funcionarios.add(inserirFuncionario("João", "12/05/1990", 2284.38, "Operador"));
        funcionarios.add(inserirFuncionario("Caio", "02/05/1961", 9836.14, "Coordenador"));
        funcionarios.add(inserirFuncionario("Miguel", "14/10/1988", 19119.88, "Diretor"));
        funcionarios.add(inserirFuncionario("Alice", "05/01/1995", 2234.68, "Recepcionista"));
        funcionarios.add(inserirFuncionario("Heitor", "19/11/1999", 1582.72, "Operador"));
        funcionarios.add(inserirFuncionario("Arthur", "31/03/1993", 4071.84, "Contador"));
        funcionarios.add(inserirFuncionario("Laura", "08/07/1994", 3017.45, "Gerente"));
        funcionarios.add(inserirFuncionario("Heloísa", "24/05/2003", 1606.85, "Eletricista"));
        funcionarios.add(inserirFuncionario("Helena", "02/09/1996", 2799.93, "Gerente"));

        return funcionarios;
    }
}
