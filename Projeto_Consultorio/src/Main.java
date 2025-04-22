import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection conexao = ConexaoBD.conectar();
        Scanner entrada = new Scanner(System.in);
        int opcao = 0;

        do{
            System.out.println("\nMENU");
            System.out.println("1 - Cadastro de médicos");
            System.out.println("2 - Cadastro de pacientes");
            System.out.println("3 - Cadastro de consultórios");
            System.out.println("4 - Agendamentos");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();

            switch(opcao){
                case 1:
                    int opcaoMedico = 0;
                    while(opcaoMedico!=5){
                        System.out.println("\n1 - Ver médicos cadastrados");
                        System.out.println("2 - Incluir novo médico");
                        System.out.println("3 - Editar dados do médico");
                        System.out.println("4 - Remover médico");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha uma opção: ");
                        opcaoMedico = entrada.nextInt();

                        switch(opcaoMedico){
                            case 1:
                                Medico.mostrarMedicos();
                                break;
                            case 2:
                                Medico.mostrarMedicos();
                                Medico.cadastrarMedico();
                                break;
                            case 3:
                                Medico.mostrarMedicos();
                                Medico.alterarMedico();
                                break;
                            case 4:
                                Medico.mostrarMedicos();
                                Medico.deletarMedico();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                    break;
                case 2:
                    int opcaoPaciente = 0;
                    while(opcaoPaciente!=5){
                        System.out.println("\n1 - Ver pacientes cadastrados");
                        System.out.println("2 - Incluir novo paciente");
                        System.out.println("3 - Editar dados do paciente");
                        System.out.println("4 - Remover paciente");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha uma opção: ");
                        opcaoPaciente = entrada.nextInt();

                        switch(opcaoPaciente){
                            case 1:
                                Paciente.mostrarPacientes();
                                break;
                            case 2:
                                Paciente.mostrarPacientes();
                                Paciente.cadastrarPaciente();
                                break;
                            case 3:
                                Paciente.mostrarPacientes();
                                Paciente.alterarPaciente();
                                break;
                            case 4:
                                Paciente.mostrarPacientes();
                                Paciente.deletarPaciente();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                    break;
                case 3:
                    int opcaoConsultorio = 0;
                    while(opcaoConsultorio !=5){
                        System.out.println("\n1 - Ver consultórios cadastrados");
                        System.out.println("2 - Incluir novo consultório");
                        System.out.println("3 - Editar dados do consultório");
                        System.out.println("4 - Remover consultório");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha uma opção: ");
                        opcaoConsultorio = entrada.nextInt();

                        switch(opcaoConsultorio){
                            case 1:
                                Consultorio.mostrarConsultorios();
                                break;
                            case 2:
                                Consultorio.mostrarConsultorios();
                                Consultorio.cadastrarConsultorio();
                                break;
                            case 3:
                                Consultorio.mostrarConsultorios();
                                Consultorio.alterarConsultorio();
                                break;
                            case 4:
                                Consultorio.mostrarConsultorios();
                                Consultorio.deletarConsultorio();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                    break;
                case 4:
                    int opcaoAgendamento = 0;
                    while(opcaoAgendamento !=5){
                        System.out.println("\n1 - Ver agendamentos");
                        System.out.println("2 - Incluir novo agendamento");
                        System.out.println("3 - Editar dados do agendamento");
                        System.out.println("4 - Remover agendamento");
                        System.out.println("5 - Voltar");
                        System.out.print("Escolha uma opção: ");
                        opcaoAgendamento = entrada.nextInt();

                        switch(opcaoAgendamento){
                            case 1:
                                Agendamento.mostrarAgendamentos();
                                break;
                            case 2:
                                Agendamento.mostrarAgendamentos();
                                Agendamento.agendarConsulta();
                                break;
                            case 3:
                                Agendamento.mostrarAgendamentos();
                                //alterar
                                break;
                            case 4:
                                Agendamento.mostrarAgendamentos();
                                //deletar
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }while(opcao != 5);

    }
}
