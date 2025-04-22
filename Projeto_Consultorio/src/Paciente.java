import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class Paciente {

    private int idPaciente;
    private String nomePaciente;
    private String dataNascimento;
    private String telefone;
    private String endereco;

    private static ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();

    //getters e setters

    public Paciente(){
    }

    public Paciente(String nomePaciente, String dataNascimento, String telefone,
                    String endereco) {
        this.idPaciente = listaPacientes.size();
        this.nomePaciente = nomePaciente;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public static void cadastrarPaciente(){
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite o nome do paciente: ");
        String nomePaciente = entrada.nextLine();
        System.out.print("Digite a data de nascimento do paciente: ");
        String dataNascimento = entrada.nextLine();
        System.out.print("Digite o telefone do paciente: ");
        String telefone = entrada.nextLine();
        System.out.print("Digite o endereço do paciente: ");
        String endereco = entrada.nextLine();

        Paciente novoPaciente = new Paciente(nomePaciente, dataNascimento, telefone,
                endereco);
        String sql = "INSERT INTO paciente (nomePaciente, dataNascimento, telefone, endereco) " +
                "VALUES ('" + nomePaciente + "', '" + dataNascimento + "', '" +
                telefone + "', '" + endereco + "');";

        boolean salvo = ConexaoBD.salvar(sql);

        if(salvo){
            System.out.println("\nPaciente cadastrado com sucesso!\n");
        }else{
            System.out.println("\nProblemas ao adicionar o paciente!\n");
        }
        //listaPacientes.add(novoPaciente);
    }

    public static void mostrarPacientes() throws SQLException {

        String sql = "SELECT * FROM paciente;";
        ResultSet resultado = ConexaoBD.buscar(sql);
        listaPacientes = new ArrayList<Paciente>();
        while(resultado.next()){
            Paciente pacienteEncontrado = new Paciente();
            pacienteEncontrado.idPaciente = resultado.getInt("idPaciente");
            pacienteEncontrado.nomePaciente = resultado.getString("nomePaciente");
            pacienteEncontrado.dataNascimento = resultado.getString("dataNascimento");
            pacienteEncontrado.telefone = resultado.getString("telefone");
            pacienteEncontrado.endereco = resultado.getString("endereco");
            listaPacientes.add(pacienteEncontrado);
        }

        System.out.print("\n\nPACIENTES CADASTRADOS: ");
        System.out.println(listaPacientes.size());
        System.out.println("ID \t Nome \t\t Data de Nascimento \t\t Telefone \t\t Endereço");

        for(int i=0; i < listaPacientes.size() ; i++){
            System.out.println(listaPacientes.get(i).idPaciente +
                    "\t" + listaPacientes.get(i).nomePaciente +
                    "\t\t" + listaPacientes.get(i).dataNascimento +
                    "\t\t" + listaPacientes.get(i).telefone +
                    "\t\t" + listaPacientes.get(i).endereco);
        }
        System.out.println("\n\n");
    }

    public static void deletarPaciente(){
        Scanner entrada = new Scanner(System.in);
        int pacienteExcluido;
        System.out.print("Informe o ID do paciente que deseja deletar: ");
        pacienteExcluido = entrada.nextInt();
        String sql = "DELETE FROM paciente WHERE idPaciente = " + pacienteExcluido;
        boolean dadoExcluido = ConexaoBD.deletar(sql);
        //listaMedicos.remove(medicoExcluido);
        if(dadoExcluido) {
            System.out.println("Paciente removido com sucesso!\n");
        }else{
            System.out.println("Problemas ao excluir o paciente!\n");
        }
    }

    public static void alterarPaciente() throws SQLException {
        Scanner entrada = new Scanner(System.in);
        if (listaPacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        System.out.print("Escolha o paciente que deseja alterar:");
        int opcao = entrada.nextInt();
        Paciente paciente = listaPacientes.get(opcao);

        ArrayList<String> alteracoes = new ArrayList<String>();

        System.out.println("Alterando dados do paciente: " + paciente.nomePaciente);
        System.out.print("Novo nome (ou enter para manter): ");
        entrada.nextLine();
        String novoNome = entrada.nextLine();
        if (!novoNome.isEmpty()) {
            alteracoes.add("UPDATE paciente SET nomePaciente = '" + novoNome +
                    "' WHERE idPaciente = " + opcao + ";");
            paciente.nomePaciente = novoNome;
        }

        System.out.print("Nova data de nascimento (ou enter para manter): ");
        String novaDataNascimento = entrada.nextLine();
        if (!novaDataNascimento.isEmpty()) {
            alteracoes.add("UPDATE paciente SET dataNascimento = '" + novaDataNascimento +
                    "' WHERE idPaciente = " + opcao + ";");
            paciente.dataNascimento = novaDataNascimento;
        }

        System.out.print("Novo telefone (ou enter para manter): ");
        String novoTelefone = entrada.nextLine();
        if (!novoTelefone.isEmpty()) {
            alteracoes.add("UPDATE paciente SET telefone = '" + novoTelefone +
                    "' WHERE idPaciente = " + opcao + ";");
            paciente.telefone = novoTelefone;
        }

        System.out.print("Novo endereço (ou enter para manter): ");
        String novoEndereco = entrada.nextLine();
        if (!novoEndereco.isEmpty()) {
            alteracoes.add("UPDATE paciente SET endereco = '" + novoEndereco +
                    "' WHERE idPaciente = " + opcao + ";");
            paciente.endereco = novoEndereco;
        }

        if(alteracoes.size()>0){
            for (int i=0; i<alteracoes.size(); i++){
                boolean dadosAlterados = ConexaoBD.atualizar(alteracoes.get(i));
            }
        }
        System.out.println("Dados do paciente atualizados com sucesso!\n");
    }

}