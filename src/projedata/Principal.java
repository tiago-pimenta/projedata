package projedata;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

	public static void main(String[] args) {

		// 3.1 - Inserir todos os funcionários
		List<Funcionario> funcionarios = new ArrayList<>();
		
		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 02), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
		

		// 3.2 - Remover o funcionário "João" da lista
		funcionarios.removeIf(funcionario -> funcionario.nome.equals("João"));

		// 3.3 - Imprimir todos os funcionários
		funcionarios.forEach(funcionario -> System.out.println(funcionario.nome + " - " + 
															   funcionario.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " + 
															   String.format("%,.2f", funcionario.salario) + " - " + funcionario.funcao));

		// 3.4 - Aumentar o salário em 10%
		funcionarios.forEach(funcionario -> funcionario.salario = funcionario.salario.multiply(new BigDecimal("1.10")));

		// 3.5 - Agrupar os funcionários por função em um MAP
		Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
				.collect(Collectors.groupingBy(Funcionario::getFuncao));

		// 3.6 - Imprimir os funcionários agrupados por função
		funcionariosPorFuncao.forEach((funcao, lista) -> {
															System.out.println("Função: " + funcao);
															lista.forEach(funcionario -> System.out.println("  " + funcionario.nome));
														 });

		// 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12
		funcionarios.stream()
				.filter(funcionario -> funcionario.dataNascimento.getMonthValue() == 10 || funcionario.dataNascimento.getMonthValue() == 12)
				.forEach(funcionario -> System.out.println(funcionario.nome));

		// 3.9 - Imprimir o funcionário com a maior idade
		Funcionario funcionarioMaisVelho = Collections.max(funcionarios, Comparator.comparing(funcionario -> funcionario.dataNascimento));
		
		int idade = Period.between(funcionarioMaisVelho.dataNascimento, LocalDate.now()).getYears();
		
		System.out.println("Funcionário mais velho: " + funcionarioMaisVelho.nome + " - Idade: " + idade);

		// 3.10 - Imprimir a lista de funcionários por ordem alfabética
		funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome))
				.forEach(funcionario -> System.out.println(funcionario.nome));

		// 3.11 - Imprimir o total dos salários dos funcionários
		BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		System.out.println("Total dos salários: " + String.format("%,.2f", totalSalarios));

		// 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
		BigDecimal salarioMinimo = new BigDecimal("1212.00");
		
		funcionarios.forEach(funcionario -> {
												BigDecimal salariosMinimos = funcionario.salario.divide(salarioMinimo, 2, RoundingMode.DOWN);
												System.out.println(funcionario.nome + " - Salários Mínimos: " + salariosMinimos);
											});

	}

}
