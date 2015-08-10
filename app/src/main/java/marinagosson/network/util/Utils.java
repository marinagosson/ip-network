package marinagosson.network.util;

import marinagosson.network.bean.Subnet;

/**
 * Created by Marina on 20/04/2015.
 */
public class Utils {

    private static final int QUANTIDADE_DE_OCTETOS = 4;

    public static Subnet obterInformacoesRede(String ipDecimal, String mascara) {

        String[] ipIpv4 = ipEmArray(ipDecimal);
        String[] ipMascara = ipEmArray(mascara);

        Subnet subnet = new Subnet();

        String[] ipDaRede = new String[QUANTIDADE_DE_OCTETOS];
        String[] ipDoBroadcast = new String[QUANTIDADE_DE_OCTETOS];

        for (int i = 0; i < 4; i++) {

            byte octetoTempMascara = (byte) ~Integer.parseInt(ipMascara[i]);

            ipDaRede[i] = Integer.toString(Integer.parseInt(ipIpv4[i]) & Integer.parseInt(ipMascara[i]));

            int resultado = Integer.parseInt(ipIpv4[i]) | octetoTempMascara;

            if (resultado == -1)
                ipDoBroadcast[i] = "255";
            else
                ipDoBroadcast[i] = Integer.toString(resultado);

        }

        subnet.setIpRede(estruturarIp(ipDaRede));
        subnet.setIpBroadcast(estruturarIp(ipDoBroadcast));

        // definir o range final, sobrescresvendo vetor de broadcast
        ipDaRede[3] = Integer.toString(Integer.parseInt(ipDaRede[3]) + 1);
        subnet.setRangeInicial(estruturarIp(ipDaRede));

        // definir o range final, sobrescresvendo vetor de broadcast
        ipDoBroadcast[3] = Integer.toString(Integer.parseInt(ipDoBroadcast[3]) - 1);
        subnet.setRangeFinal(estruturarIp(ipDoBroadcast));

        subnet.setClasse(classificarRede(Integer.parseInt(ipIpv4[0])));

        return subnet;

    }

    private static String[] ipEmArray(String ip) {
        String[] toReturn = ip.split("\\.");
        return toReturn;
    }

    private static String estruturarIp(String[] ipArray) {
        String toReturn = "";
        for (int i = 0; i < ipArray.length; i++)
            toReturn = toReturn + ipArray[i] + ".";
        return toReturn.substring(0, toReturn.length() - 1);
    }

    public static String classificarRede(int octetoPrincipal) {

        if (octetoPrincipal >= 10 && octetoPrincipal <= 127) {
            return "Classe A";
        } else if (octetoPrincipal >= 128 && octetoPrincipal <= 191) {
            return "Classe B";
        } else if (octetoPrincipal >= 192 && octetoPrincipal <= 223) {
            return "Classe C";
        } else if (octetoPrincipal >= 224 && octetoPrincipal <= 239) {
            return "Classe D";
        } else if (octetoPrincipal >= 240 && octetoPrincipal <= 255) {
            return "Classe E";
        }

        return "Classe não definida";

    }

    public static String decimalParaBinario(String decimal){
        return Integer.toBinaryString(Integer.parseInt(decimal));
    }

    public static String binarioParaDecimal(String binario){
        return Integer.toString(Integer.parseInt(binario,2));
    }

}
