package com.cs.springbootestfour.pojo;


import org.beetl.ext.format.DateFormat;

import java.sql.Date;

public class Kaoshi {

  private Integer ksid;
  private String ksname;
  private String kspwd;
//  private java.time.LocalDate ksrq;
  private Date ksrq;

  public Date getKsrq() {
    return ksrq;
  }

  public void setKsrq(Date ksrq) {
    this.ksrq = ksrq;
  }

  private Integer ksxb;

  @Override
  public String toString() {
    return "Kaoshi{" +
            "ksid=" + ksid +
            ", ksname='" + ksname + '\'' +
            ", kspwd='" + kspwd + '\'' +
            ", ksrq=" + ksrq +
            ", ksxb=" + ksxb +
            '}';
  }

  public Integer getKsid() {
    return ksid;
  }

  public void setKsid(Integer ksid) {
    this.ksid = ksid;
  }


  public String getKsname() {
    return ksname;
  }

  public void setKsname(String ksname) {
    this.ksname = ksname;
  }


  public String getKspwd() {
    return kspwd;
  }

  public void setKspwd(String kspwd) {
    this.kspwd = kspwd;
  }





  public Integer getKsxb() {
    return ksxb;
  }

  public void setKsxb(Integer ksxb) {
    this.ksxb = ksxb;
  }

}
