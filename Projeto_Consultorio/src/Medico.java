import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class Medico {

    private int idMedico;
    private String nomeMedico;
    private String crm;
    private String especialidade;
    private String telefone;
    private String periodoAtendimento;

    private static ArrayList<Medico> listaMedicos = new ArrayList<Medico>();

    public static ArrayList<Medico> getListaMedicos() {
        return listaMedicos;
    }

    public Medico(){
    }

    public Medico(String nomeMedico, String crm, String especialidade, String telefone, String periodoAtendimento){
        this.idMedico = listaMedicos.size();
        this.nomeMedico = nomeMedico;
        this.crm = crm;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.periodoAtendimento = periodoAtendimento;
    }

    //getters e setters

    public static void cadastrarMedico(){
        Scanner entrada = new Scanner(System.in);

        //Medico novoMedico = new Medico();

        System.out.print("Digite o nome do médico: ");
        String nomeMedico = entrada.nextLine();
        System.out.print("Digite o CRM do médico: ");
        String crm = entrada.nextLine();
        System.out.print("Digite a especialidade do médico: ");
        String especialidade = entrada.nextLine();
        System.out.print("Digite o telefone do médico: ");
        String telefone = entrada.nextLine();
        System.out.print("Digite o período de atendimento do médico: ");
        String periodoAtendimento = entrada.nextLine();

        Medico novoMedico = new Medico(nomeMedico, crm, especialidade, telefone, periodoAtendimento);

        String sql = "INSERT INTO medico (nomeMedico, crm, especialidade, telefone, periodoAtendimento) " +
                "VALUES ('" + nomeMedico + "', '" + crm + "', '" + especialidade + "', '" +
                telefone + "', '" + periodoAtendimento + "');";

        boolean salvo = ConexaoBD.salvar(sql);

        if(salvo){
            System.out.println("\nMédico cadastrado com sucesso!\n");
        }else{
            System.out.println("\nProblemas ao adicionar o médico!\n");
        }
        //listaMedicos.add(novoMedico);
    }

    public static void mostrarMedicos() throws SQLException {

        String sql = "SELECT * FROM medico;";
        ResultSet resultado = ConexaoBD.buscar(sql);
        listaMedicos = new ArrayList<Medico>();
        while(resultado.next()){
            Medico medicoEncontrado = new Medico();
            medicoEncontrado.idMedico = resultado.getInt("idMedico");
            medicoEncontrado.nomeMedico = resultado.getString("nomeMedico");
            medicoEncontrado.crm = resultado.getString("crm");
            medicoEncontrado.especialidade = resultado.getString("especialidade");
            medicoEncontrado.telefone = resultado.getString("telefone");
            medicoEncontrado.periodoAtendimento = resultado.getString("periodoAtendimento");
            listaMedicos.add(medicoEncontrado);
        }

        System.out.print("\n\nMÉDICOS CADASTRADOS: ");
        System.out.println(listaMedicos.size());
        System.out.println("ID \t Nome \t\t CRM \t\t Especialidade \t\t Telefone \t\t Período");

        for(int i=0; i < listaMedicos.size() ; i++){
            System.out.println(listaMedicos.get(i).idMedico +
                    "\t" + listaMedicos.get(i).nomeMedico +
                    "\t\t" + listaMedicos.get(i).crm +
                    "\t\t" + listaMedicos.get(i).especialidade +
                    "\t\t" + listaMedicos.get(i).telefone +
                    "\t\t" + listaMedicos.get(i).periodoAtendimento);
        }
        System.out.println("\n\n");
    }

    public static void deletarMedico(){
        Scanner entrada = new Scanner(System.in);
        int medicoExcluido;
        System.out.print("Informe o ID do médico que deseja deletar: ");
        medicoExcluido = entrada.nextInt();
        String sql = "DELETE FROM medico WHERE idMedico = " + medicoExcluido;
        boolean dadoExcluido = ConexaoBD.deletar(sql);
        //listaMedicos.remove(medicoExcluido);
        if(dadoExcluido) {
            System.out.println("Médico removido com sucesso!\n");
        }else{
            System.out.println("Problemas ao excluir o médico!\n");
        }
    }

    public static void alterarMedico() throws SQLException {
        Scanner entrada = new Scanner(System.in);
        if (listaMedicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }

        System.out.print("Escolha o médico que deseja alterar:");
        /*
        for (int i = 0; i < listaMedicos.size(); i++) {
            System.out.println(listaMedicos.get(i).idMedico + " - " + listaMedicos.get(i).nomeMedico);
        }
        */

        int opcao = entrada.nextInt();
        Medico medico = listaMedicos.get(opcao);

        ArrayList<String> alteracoes = new ArrayList<String>();

        System.out.println("Alterando dados do médico: " + medico.nomeMedico);
        System.out.print("Novo nome (ou enter para manter): ");
        entrada.nextLine();
        String novoNome = entrada.nextLine();
        if (!novoNome.isEmpty()) {
            alteracoes.add("UPDATE medico SET nomeMedico = '" + novoNome +
                    "' WHERE idMedico = " + opcao + ";");
            medico.nomeMedico = novoNome;
        }

        System.out.print("Nova especialidade (ou enter para manter): ");
        String novaEspecialidade = entrada.nextLine();
        if (!novaEspecialidade.isEmpty()) {
            alteracoes.add("UPDATE medico SET especialidade = '" + novaEspecialidade +
                    "' WHERE idMedico = " + opcao + ";");
            medico.especialidade = novaEspecialidade;
        }

        System.out.print("Novo CRM (ou enter para manter): ");
        String novoCrm = entrada.nextLine();
        if (!novoCrm.isEmpty()) {
            alteracoes.add("UPDATE medico SET crm = '" + novoCrm +
                    "' WHERE idMedico = " + opcao + ";");
            medico.crm = novoCrm;
        }

        System.out.print("Novo telefone (ou enter para manter): ");
        String novoTelefone = entrada.nextLine();
        if (!novoTelefone.isEmpty()) {
            alteracoes.add("UPDATE medico SET telefone = '" + novoTelefone +
                    "' WHERE idMedico = " + opcao + ";");
            medico.telefone = novoTelefone;
        }

        System.out.print("Novo período de atendimento (ou enter para manter): ");
        String novoPeriodoAtendimento = entrada.nextLine();
        if (!novoPeriodoAtendimento.isEmpty()) {
            alteracoes.add("UPDATE medico SET periodoAtendimento = '" + novoPeriodoAtendimento +
                    "' WHERE idMedico = " + opcao + ";");
            medico.periodoAtendimento = novoPeriodoAtendimento;
        }

        if(alteracoes.size()>0){
            for (int i=0; i<alteracoes.size(); i++){
                boolean dadosAlterados = ConexaoBD.atualizar(alteracoes.get(i));
            }
        }
        System.out.println("Dados do médico atualizados com sucesso!\n");
    }
}