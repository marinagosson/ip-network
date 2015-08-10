package marinagosson.network.bean;

/**
 * Created by Marina on 27/04/2015.
 */
public class Subnet {

    private String ipRede;
    private String rangeInicial;
    private String rangeFinal;
    private String ipBroadcast;
    private String classe;

    public Subnet() {
        super();
    }

    public Subnet(String ipRede, String rangeInicial, String rangeFinal, String ipBroadcast) {
        this.ipRede = ipRede;
        this.rangeInicial = rangeInicial;
        this.rangeFinal = rangeFinal;
        this.ipBroadcast = ipBroadcast;
    }

    public String getClasse() {
        return classe;
    }

    public String getRangeInicial() {
        return rangeInicial;
    }

    public void setRangeInicial(String rangeInicial) {
        this.rangeInicial = rangeInicial;
    }

    public String getIpRede() {
        return ipRede;
    }

    public void setIpRede(String ipRede) {
        this.ipRede = ipRede;
    }

    public String getRangeFinal() {
        return rangeFinal;
    }

    public void setRangeFinal(String rangeFinal) {
        this.rangeFinal = rangeFinal;
    }

    public String getIpBroadcast() {
        return ipBroadcast;
    }

    public void setIpBroadcast(String ipBroadcast) {
        this.ipBroadcast = ipBroadcast;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Ip Rede . . . . . . " + ipRede +
                "\nRange I . . . . . ." + rangeInicial +
                "\nRange F . . . . . " + rangeFinal +
                "\nIp Broadcast. . ." + ipBroadcast +
                "\n" + classe;
    }
}
