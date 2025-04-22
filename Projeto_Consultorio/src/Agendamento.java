import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Agendamento {

    private int idAgendamento;
    private String horario;
    private String data;
    private int idPaciente;
    private int idMedico;
    private int idConsultorio;

    private static ArrayList<Agendamento> listaAgendamentos = new ArrayList<Agendamento>();

    public Agendamento() {
    }

    // Construtor preenchido
    public Agendamento(String horario, String data, int idPaciente, int idMedico, int idConsultorio) {
        this.idAgendamento = listaAgendamentos.size();
        this.horario = horario;
        this.data = data;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.idConsultorio = idConsultorio;
    }

    public static void agendarConsulta() throws SQLException {

        Scanner entrada = new Scanner(System.in);
        System.out.println("Agendamento de Consultas:");
        System.out.print("Digite o horário da consulta (hh:mm): ");
        String horario = entrada.next();
        System.out.print("Digite a data da consulta (dd/mm/aaaa): ");
        String data = entrada.next();

        // Seleção do consultório
        Consultorio.mostrarConsultorios();
        System.out.print("Escolha um consultório (informe o ID): ");
        int opcaoConsultorio = entrada.nextInt();

        // Seleção do médico
        Medico.mostrarMedicos();
        System.out.print("Escolha um médico (informe o ID): ");
        int opcaoMedico = entrada.nextInt();

        // Seleção do paciente
        Paciente.mostrarPacientes();
        System.out.print("Escolha um paciente (informe o ID): ");
        int opcaoPaciente = entrada.nextInt();

        // Agendamento
        Agendamento agendamento = new Agendamento(horario, data, opcaoPaciente,
                opcaoPaciente, opcaoConsultorio);

        String sql = "INSERT INTO agendamento (data, horario, idConsultorio, idMedico, idPaciente) " +
                "VALUES ('" + data + "', '" + horario + "', " + opcaoConsultorio + ", " +
                opcaoMedico + ", " + opcaoPaciente + ");";

        boolean salvo = ConexaoBD.salvar(sql);

        if(salvo){
            System.out.println("\nConsulta agendada com sucesso!\n");
        }else{
            System.out.println("\nProblemas ao agendar consulta!\n");
        }
        //listaAgendamentos.add(agendamento);
    }

    public static void mostrarAgendamentos() throws SQLException {

        String sql = "SELECT * FROM agendamento;";
        ResultSet resultado = ConexaoBD.buscar(sql);

        listaAgendamentos = new ArrayList<Agendamento>();

        while(resultado.next()){
            Agendamento agendamentoEncontrado = new Agendamento();
            agendamentoEncontrado.idAgendamento = resultado.getInt("idAgendamento");
            agendamentoEncontrado.data = resultado.getString("data");
            agendamentoEncontrado.horario = resultado.getString("horario");
            agendamentoEncontrado.idMedico = resultado.getInt("idMedico");
            agendamentoEncontrado.idPaciente = resultado.getInt("idPaciente");
            agendamentoEncontrado.idConsultorio = resultado.getInt("idConsultorio");
            listaAgendamentos.add(agendamentoEncontrado);
        }


        System.out.print("\n\nAGENDAMENTOS: ");
        System.out.println(listaAgendamentos.size());
        System.out.println("ID \t Data \t\t Horário \t\t Consultório \t\t Médico \t\t Paciente");

        for(int i=0; i < listaAgendamentos.size() ; i++){
            System.out.println(listaAgendamentos.get(i).idAgendamento +
                    "\t" + listaAgendamentos.get(i).data +
                    "\t\t" + listaAgendamentos.get(i).horario +
                    "\t\t" + listaAgendamentos.get(i).idConsultorio +
                    "\t\t" + listaAgendamentos.get(i).idMedico +
                    "\t\t" + listaAgendamentos.get(i).idPaciente);
        }
        System.out.println("\n\n");
    }


}
