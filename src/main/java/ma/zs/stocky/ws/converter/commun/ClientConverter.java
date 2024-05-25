package  ma.zs.stocky.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.commun.SocieteConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.commun.Client;
import ma.zs.stocky.ws.dto.commun.ClientDto;

@Component
public class ClientConverter {

    @Autowired
    private SocieteConverter societeConverter ;
    private boolean societe;

    public  ClientConverter() {
        initObject(true);
    }


    public Client toItem(ClientDto dto) {
        if (dto == null) {
            return null;
        } else {
        Client item = new Client();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getNumeroTelephone()))
                item.setNumeroTelephone(dto.getNumeroTelephone());
            if(StringUtil.isNotEmpty(dto.getAdresse()))
                item.setAdresse(dto.getAdresse());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getPoste()))
                item.setPoste(dto.getPoste());
            if(StringUtil.isNotEmpty(dto.getDateInscription()))
                item.setDateInscription(DateUtil.stringEnToDate(dto.getDateInscription()));
             if(this.societe && dto.getSociete()!=null)
                item.setSociete(societeConverter.toItem(dto.getSociete())) ;




        return item;
        }
    }


    public ClientDto toDto(Client item) {
        if (item == null) {
            return null;
        } else {
            ClientDto dto = new ClientDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getNumeroTelephone()))
                dto.setNumeroTelephone(item.getNumeroTelephone());
            if(StringUtil.isNotEmpty(item.getAdresse()))
                dto.setAdresse(item.getAdresse());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getPoste()))
                dto.setPoste(item.getPoste());
            if(item.getDateInscription()!=null)
                dto.setDateInscription(DateUtil.dateTimeToString(item.getDateInscription()));
            if(this.societe && item.getSociete()!=null) {
                dto.setSociete(societeConverter.toDto(item.getSociete())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.societe = value;
    }
	
    public List<Client> toItem(List<ClientDto> dtos) {
        List<Client> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ClientDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ClientDto> toDto(List<Client> items) {
        List<ClientDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Client item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ClientDto dto, Client t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getSociete() != null)
        societeConverter.copy(dto.getSociete(), t.getSociete());
    }

    public List<Client> copy(List<ClientDto> dtos) {
        List<Client> result = new ArrayList<>();
        if (dtos != null) {
            for (ClientDto dto : dtos) {
                Client instance = new Client();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public SocieteConverter getSocieteConverter(){
        return this.societeConverter;
    }
    public void setSocieteConverter(SocieteConverter societeConverter ){
        this.societeConverter = societeConverter;
    }
    public boolean  isSociete(){
        return this.societe;
    }
    public void  setSociete(boolean societe){
        this.societe = societe;
    }
}
