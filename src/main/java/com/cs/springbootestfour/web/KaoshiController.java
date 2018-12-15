package com.cs.springbootestfour.web;

import com.cs.springbootestfour.dao.KaoshiDao;
import com.cs.springbootestfour.pojo.Kaoshi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/kaoshi")
public class KaoshiController {
    @Autowired
    KaoshiDao kaoshiDao;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Kaoshi> getKaoshis(){
        return  kaoshiDao.all();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  Kaoshi getKaoshiById(@PathVariable("id") int id){
        return kaoshiDao.unique(id);
    }
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Kaoshi getKaoshiBbyId(@RequestParam("name")String name){
        return kaoshiDao.selectKaoshiByKsname(name);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updateKaoshi(
            @PathVariable("ksid")int ksid,
            @RequestParam(value = "ksname",required = true)String ksname,
            @RequestParam(value = "kspwd",required = true)String kspwd,
            @RequestParam(value = "ksxb",required = true)int  ksxb){
            Kaoshi kaoshi=new Kaoshi();
            java.sql.Date now= new java.sql.Date(System.currentTimeMillis());
            kaoshi.setKsname(ksname);
            kaoshi.setKspwd(kspwd);
            kaoshi.setKsxb(ksxb);
            kaoshi.setKsid(ksid);
            kaoshi.setKsrq(now);
            int xg=kaoshiDao.updateById(kaoshi);
            if(xg==1){
                return kaoshi.toString();
            }else {
                return "false";
            }
    }


}
