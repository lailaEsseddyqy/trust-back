package  ma.zs.stocky.ws.converter.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.article.EtatArticleConverter;
import ma.zs.stocky.ws.converter.commun.FournisseurConverter;
import ma.zs.stocky.ws.converter.article.MarqueConverter;
import ma.zs.stocky.ws.converter.article.CategorieArticleConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.article.Article;
import ma.zs.stocky.ws.dto.article.ArticleDto;

@Component
public class ArticleConverter {

    @Autowired
    private EtatArticleConverter etatArticleConverter ;
    @Autowired
    private FournisseurConverter fournisseurConverter ;
    @Autowired
    private MarqueConverter marqueConverter ;
    @Autowired
    private CategorieArticleConverter categorieArticleConverter ;
    private boolean categorieArticle;
    private boolean etatArticle;
    private boolean marque;
    private boolean fournisseur;

    public  ArticleConverter() {
        initObject(true);
    }


    public Article toItem(ArticleDto dto) {
        if (dto == null) {
            return null;
        } else {
        Article item = new Article();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getType()))
                item.setType(dto.getType());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getUniteVente()))
                item.setUniteVente(dto.getUniteVente());
            if(StringUtil.isNotEmpty(dto.getQuantite()))
                item.setQuantite(dto.getQuantite());
            if(StringUtil.isNotEmpty(dto.getQuantiteAlerte()))
                item.setQuantiteAlerte(dto.getQuantiteAlerte());
            if(StringUtil.isNotEmpty(dto.getPrixAchat()))
                item.setPrixAchat(dto.getPrixAchat());
            if(StringUtil.isNotEmpty(dto.getPrixVente()))
                item.setPrixVente(dto.getPrixVente());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.categorieArticle && dto.getCategorieArticle()!=null)
                item.setCategorieArticle(categorieArticleConverter.toItem(dto.getCategorieArticle())) ;

            if(this.etatArticle && dto.getEtatArticle()!=null)
                item.setEtatArticle(etatArticleConverter.toItem(dto.getEtatArticle())) ;

            if(this.marque && dto.getMarque()!=null)
                item.setMarque(marqueConverter.toItem(dto.getMarque())) ;

            if(this.fournisseur && dto.getFournisseur()!=null)
                item.setFournisseur(fournisseurConverter.toItem(dto.getFournisseur())) ;




        return item;
        }
    }


    public ArticleDto toDto(Article item) {
        if (item == null) {
            return null;
        } else {
            ArticleDto dto = new ArticleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getType()))
                dto.setType(item.getType());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(StringUtil.isNotEmpty(item.getUniteVente()))
                dto.setUniteVente(item.getUniteVente());
            if(StringUtil.isNotEmpty(item.getQuantite()))
                dto.setQuantite(item.getQuantite());
            if(StringUtil.isNotEmpty(item.getQuantiteAlerte()))
                dto.setQuantiteAlerte(item.getQuantiteAlerte());
            if(StringUtil.isNotEmpty(item.getPrixAchat()))
                dto.setPrixAchat(item.getPrixAchat());
            if(StringUtil.isNotEmpty(item.getPrixVente()))
                dto.setPrixVente(item.getPrixVente());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.categorieArticle && item.getCategorieArticle()!=null) {
                dto.setCategorieArticle(categorieArticleConverter.toDto(item.getCategorieArticle())) ;

            }
            if(this.etatArticle && item.getEtatArticle()!=null) {
                etatArticleConverter.setArticle(false);
                dto.setEtatArticle(etatArticleConverter.toDto(item.getEtatArticle())) ;
                etatArticleConverter.setArticle(true);

            }
            if(this.marque && item.getMarque()!=null) {
                dto.setMarque(marqueConverter.toDto(item.getMarque())) ;

            }
            if(this.fournisseur && item.getFournisseur()!=null) {
                dto.setFournisseur(fournisseurConverter.toDto(item.getFournisseur())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.categorieArticle = value;
        this.etatArticle = value;
        this.marque = value;
        this.fournisseur = value;
    }
	
    public List<Article> toItem(List<ArticleDto> dtos) {
        List<Article> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ArticleDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ArticleDto> toDto(List<Article> items) {
        List<ArticleDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Article item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ArticleDto dto, Article t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getCategorieArticle() != null)
        categorieArticleConverter.copy(dto.getCategorieArticle(), t.getCategorieArticle());
        if (dto.getEtatArticle() != null)
        etatArticleConverter.copy(dto.getEtatArticle(), t.getEtatArticle());
        if (dto.getMarque() != null)
        marqueConverter.copy(dto.getMarque(), t.getMarque());
        if (dto.getFournisseur() != null)
        fournisseurConverter.copy(dto.getFournisseur(), t.getFournisseur());
    }

    public List<Article> copy(List<ArticleDto> dtos) {
        List<Article> result = new ArrayList<>();
        if (dtos != null) {
            for (ArticleDto dto : dtos) {
                Article instance = new Article();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EtatArticleConverter getEtatArticleConverter(){
        return this.etatArticleConverter;
    }
    public void setEtatArticleConverter(EtatArticleConverter etatArticleConverter ){
        this.etatArticleConverter = etatArticleConverter;
    }
    public FournisseurConverter getFournisseurConverter(){
        return this.fournisseurConverter;
    }
    public void setFournisseurConverter(FournisseurConverter fournisseurConverter ){
        this.fournisseurConverter = fournisseurConverter;
    }
    public MarqueConverter getMarqueConverter(){
        return this.marqueConverter;
    }
    public void setMarqueConverter(MarqueConverter marqueConverter ){
        this.marqueConverter = marqueConverter;
    }
    public CategorieArticleConverter getCategorieArticleConverter(){
        return this.categorieArticleConverter;
    }
    public void setCategorieArticleConverter(CategorieArticleConverter categorieArticleConverter ){
        this.categorieArticleConverter = categorieArticleConverter;
    }
    public boolean  isCategorieArticle(){
        return this.categorieArticle;
    }
    public void  setCategorieArticle(boolean categorieArticle){
        this.categorieArticle = categorieArticle;
    }
    public boolean  isEtatArticle(){
        return this.etatArticle;
    }
    public void  setEtatArticle(boolean etatArticle){
        this.etatArticle = etatArticle;
    }
    public boolean  isMarque(){
        return this.marque;
    }
    public void  setMarque(boolean marque){
        this.marque = marque;
    }
    public boolean  isFournisseur(){
        return this.fournisseur;
    }
    public void  setFournisseur(boolean fournisseur){
        this.fournisseur = fournisseur;
    }
}
