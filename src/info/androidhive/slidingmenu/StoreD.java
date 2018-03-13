package info.androidhive.slidingmenu;

public class StoreD {
String empid,inisid,bna, rsn, ssnid,fnh,fd,td,letyp;

public StoreD(String empid, String inisid, String bna, String rsn, String ssnid, String fnh, String fd, String td, String lt )
{
super();
this.empid=empid;
this.inisid=inisid;
this.bna=bna;
this.rsn=rsn;
this.ssnid=ssnid;
this.fnh=fnh;
this.fd=fd;
this.td=td;
this.letyp=lt;
}

public StoreD()
{
super();
this.empid=null;
this.inisid=null;
this.bna=null;
this.rsn=null;
this.ssnid=null;
this.fnh=null;
this.fd=null;
this.td=null;
this.letyp=null;
}

public String getEmpid() {
	return empid;
}

public void setEmpid(String empid) {
	this.empid = empid;
}

public String getInisid() {
	return inisid;
}

public void setInisid(String inisid) {
	this.inisid = inisid;
}

public String getBna() {
	return bna;
}

public void setBna(String bna) {
	this.bna = bna;
}

public String getRsn() {
	return rsn;
}

public void setRsn(String rsn) {
	this.rsn = rsn;
}

public String getSsnid() {
	return ssnid;
}

public void setSsnid(String ssnid) {
	this.ssnid = ssnid;
}

public String getFnh() {
	return fnh;
}

public void setFnh(String fnh) {
	this.fnh = fnh;
}

public String getFd() {
	return fd;
}

public void setFd(String fd) {
	this.fd = fd;
}

public String getTd() {
	return td;
}

public void setTd(String td) {
	this.td = td;
}

public String getLetyp() {
	return letyp;
}

public void setLetyp(String letyp) {
	this.letyp = letyp;
}


}