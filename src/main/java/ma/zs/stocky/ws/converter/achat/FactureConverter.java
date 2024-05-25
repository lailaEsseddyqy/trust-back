package  ma.zs.stocky.ws.converter.achat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.achat.TypeFactureConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.achat.Facture;
import ma.zs.stocky.ws.dto.achat.FactureDto;

@Component
public class FactureConverter {

    @Autowired
    private TypeFactureConverter typeFactureConverter ;
    private boolean typeFacture;

    public  FactureConverter() {
        initObject(true);
    }


    public Facture toItem(FactureDto dto) {
        if (dto == null) {
            return null;
        } else {
        Facture item = new Facture();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getDateFacture()))
                item.setDateFacture(DateUtil.stringEnToDate(dto.getDateFacture()));
            if(StringUtil.isNotEmpty(dto.getDateLimite()))
                item.setDateLimite(DateUtil.stringEnToDate(dto.getDateLimite()));
            if(StringUtil.isNotEmpty(dto.getMontantHt()))
                item.setMontantHt(dto.getMontantHt());
            if(StringUtil.isNotEmpty(dto.getMontantTtc()))
                item.setMontantTtc(dto.getMontantTtc());
            if(StringUtil.isNotEmpty(dto.getMontantTva()))
                item.setMontantTva(dto.getMontantTva());
            if(StringUtil.isNotEmpty(dto.getRemise()))
                item.setRemise(dto.getRemise());

            item.setPaid(dto.isPaid());
            if(this.typeFacture && dto.getTypeFacture()!=null)
                item.setTypeFacture(typeFactureConverter.toItem(dto.getTypeFacture())) ;




        return item;
        }
    }


    public FactureDto toDto(Facture item) {
        if (item == null) {
            return null;
        } else {
            FactureDto dto = new FactureDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(item.getDateFacture()!=null)
                dto.setDateFacture(DateUtil.dateTimeToString(item.getDateFacture()));
            if(item.getDateLimite()!=null)
                dto.setDateLimite(DateUtil.dateTimeToString(item.getDateLimite()));
            if(StringUtil.isNotEmpty(item.getMontantHt()))
                dto.setMontantHt(item.getMontantHt());
            if(StringUtil.isNotEmpty(item.getMontantTtc()))
                dto.setMontantTtc(item.getMontantTtc());
            if(StringUtil.isNotEmpty(item.getMontantTva()))
                dto.setMontantTva(item.getMontantTva());
            if(StringUtil.isNotEmpty(item.getRemise()))
                dto.setRemise(item.getRemise());

            dto.setPaid(item.isPaid());
            if(this.typeFacture && item.getTypeFacture()!=null) {
                dto.setTypeFacture(typeFactureConverter.toDto(item.getTypeFacture())) ;
            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.typeFacture = value;
    }
	
    public List<Facture> toItem(List<FactureDto> dtos) {
        List<Facture> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (FactureDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<FactureDto> toDto(List<Facture> items) {
        List<FactureDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Facture item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(FactureDto dto, Facture t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getTypeFacture() != null)
        typeFactureConverter.copy(dto.getTypeFacture(), t.getTypeFacture());
    }

    public List<Facture> copy(List<FactureDto> dtos) {
        List<Facture> result = new ArrayList<>();
        if (dtos != null) {
            for (FactureDto dto : dtos) {
                Facture instance = new Facture();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TypeFactureConverter getTypeFactureConverter(){
        return this.typeFactureConverter;
    }
    public void setTypeFactureConverter(TypeFactureConverter typeFactureConverter ){
        this.typeFactureConverter = typeFactureConverter;
    }
    public boolean  isTypeFacture(){
        return this.typeFacture;
    }
    public void  setTypeFacture(boolean typeFacture){
        this.typeFacture = typeFacture;
    }
}
