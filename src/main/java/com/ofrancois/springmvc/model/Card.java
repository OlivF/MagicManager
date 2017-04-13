package com.ofrancois.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;

/** 
 * <b>Card est la classe représentant une carte de la collection que l'on souhaite créer.</b>
 * <p>
 * Une carte est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribué définitivement</li>
 * <li>Un nom français</li>
 * <li>Un nom anglais</li>
 * <li>Un type @see Type</li>
 * <li>Une édition @see Edition</li>
 * <li>Un coût de mana</li>
 * <li>Une rareté</li>
 * <li>Un prix</li>
 * <li>Une quantité</li>
 * <li>Une quantité disponible</li> 
 * </ul>
 * </p>
 * <p>
 * De plus, une carte pourra être ajouté à un ou plusieurs decks suivant la disponibilité de celle-ci.
 * </p>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Entity
@Table ( name = "card")
public class Card {
	
	/**
	 * L'ID de la Carte. Cet ID n'est pas modifiable
	 * 
	 * @see Card#Card(long, String, String, String, float, int);
	 * @see Card#getId()
	 */
	private long id;
    
	/**
	 * Le nom français de la carte
	 * 
	 * @see Card#setNameFr(String)
	 * @see Card#getNameFr()
	 */
    private String nameFr;
    
    /**
     * Le nom anglais de la carte
     * 
     * @see Card#setNameEn(String)
     * @see Card#getNameEn()
     */
    private String nameEn;
    
    /**
     * Le type de la carte
     * 
     * @see Card#setType(Type)
     * @see Card#getType()
     */
    private Type type;
    
    /**
     * L'édition de la carte
     * 
     * @see Card#setEdition(Edition)
     * @see Card#getEdition()
     */
    private Edition edition;
     
    /**
     * Le coût de mana de la carte
     * 
     * @see Card#setManaCost(String)
     * @see Card#getManaCost()
     * 
     */
    private String manaCost;
    
    /**
     * La rareté de la carte
     * 
     * @see Card#setRarity(Rarity)
     * @see Card#getRarity()
     */
    private Rarity rarity;
    
    /**
     * Le prix de la carte selon la côte magic bazar
     * 
     * @see Card#setPrice(float)
     * @see Card#getPrice()
     */
    private float price;
    
    /**
     * La quantité de cette carte, sachant que l'on peut en mettre jusqu'à 4 par deck
     * 
     * @see Card#setNbItem(int)
     * @see Card#getNbItem()
     */
    private int nbItem;
    
    /**
     * La quantité de carte n'étant pas encore utilisée dans des decks
     * 
     * @see Card#setNbDispo(int)
     * @see Card#getNbDispo()
     */
    private int nbDispo;
    
    /**
     * La date de création ou de modification de la carte dans la base de donnée
     * 
     * @see Card#setDate(Date)
     * @see Card#getDate()
     */
    private Date date;
    
    /**
     * Constructeur Card.
     *
     * @param id
     * 				L'identifiant unique de la carte
     * @param nameFr
     * 				Le nom français de la carte
     * @param nameEn
     * 				Le nom anglais de la carte
     * @param manaCost
     * 				Le coût de mana de la carte
     * @param price
     * 				Le prix de la carte
     * @param nbItem
     * 				La quantité de la carte
     */
    public Card( long id, String nameFr, String nameEn, String manaCost, float price, int nbItem ) {
        this.setId( id );
        this.setNameFr( nameFr );
        this.setNameEn( nameEn );
        this.setManaCost( manaCost );
        this.setPrice( price );
        this.setNbItem( nbItem );
    }
 
    /**
     * Retourne l'id de la carte
     * Ce champs est relié à la base de données grâce à une annotation
     * 
     * @return L'identifiant de la carte
     */
    @Id
	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public long getId() {
        return id;
    }
 
    /**
     * Met à jour l'identifiant de la carte
     * 
     * @param id
     * 				Le nouvel identifiant de la carte
     */
    public void setId( long id ) {
    	if ( id < 0 ) throw new RuntimeException( "Id must be positive" ); 
        this.id = id;
    }
 
    /**
     * Retourne le nom français de la carte
     * 
     * Ce champs est relié à la base de données grâce à une annotation
     * 
     * @return Le nom français de la carte, sous forme d'une chaine de caractère
     */
    @Column(name = "NAMEFR")
    public String getNameFr() {
        return nameFr;
    }
 
    /**
     * Met à jour le nom français de la carte
     * 
     * @param nameFr
     * 					Le nouveau nom français de la carte
     */
    public void setNameFr(String nameFr) {
    	if ( nameFr == null || nameFr.trim().equals("") ) throw new RuntimeException( "NameFr cannot be empty" );
        this.nameFr = nameFr;
    }
    
    /**
     * Retourne le nom anglais de la carte
     * 
     * Ce champs est relié à la base de données grâce à une annotation
     *
     * @return Le nom anglais de la carte, sous forme d'une chaine de caractère
     */
    @Column(name = "NAMEEN")
    public String getNameEn() {
        return nameEn;
    }
 
    /**
     * Met à jour le nom anglais de la carte
     * 
     * @param nameEn
     * 					Le nouveau nom anglais de la carte
     */
    public void setNameEn( String nameEn ) {
    	if ( nameEn == null || nameEn.trim().equals("") ) throw new RuntimeException( "NameEn cannot be empty" );
        this.nameEn = nameEn;
    }
    
    /**
     * Retourne le type de la carte
     * 
     * Ce champs est relié à la base de données grâce à une annotation
     * 
     * @return Une instance de Type, qui correspond au type de la carte
     * 
     * @see Type
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    public Type getType() {
        return type;
    }
 
    /**
     * Met à jour le type de la carte
     * 
     * @param type
     * 				Le nouveau type de la carte
     * 
     * @see Type
     */
    public void setType( Type type ) {
        this.type = type;
    }

    /**
     * Retourne l'édition de la carte
     * 
     * Ce champs est relié à la base de données grâce à une annotation
     *
     * @return Une instance de Edition, qui correspond à l'édition de la carte
     * 
     * @see Edition
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "edition_id")
    public Edition getEdition() {
        return edition;
    }
 
    /**
     * Met à jour l'édition de la carte
     * 
     * @param edition
     * 					La nouvelle édition de la carte
     * 
     * @see Edition
     */
    public void setEdition( Edition edition ) {
        this.edition = edition;
    }
    
    /**
     * Retourne le coût de mana de la carte
     * 
     * Ce champs est relié à la base de données grâce à une annotation
     * 
     * @return Le coût de mana de la carte, sous forme d'une chaîne de caractère
     */
    @Column(name = "MANACOST")
    public String getManaCost() {
        return manaCost;
    }
 
    /**
     * Met à jour le coût de mana de la carte
     * 
     * @param manaCost
     * 					Le nouveau coût de mana de la carte
     */
    public void setManaCost( String manaCost ) {
        this.manaCost = manaCost;
    }
    
    /**
     * Retourne la rareté de la carte
     * 
     * Ce champs est relié à la base de données grâce à une annotation
     * 
     * @return Une instance de Rarity, qui correspond à la rareté de la carte
     * 
     * @see Rarity
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rarity_id")
    public Rarity getRarity() {
        return rarity;
    }
 
    /**
     * Met à jour la rareté de la carte
     * 
     * @param rarity
     * 					La nouvelle rareté de la carte
     * 
     * @see Rarity
     */
    public void setRarity( Rarity rarity ) {
        this.rarity = rarity;
    }
    
    /**
     * Retourne le prix de la carte
     * 
     * Ce champs est relié à la base de données grâce à une annotation
     * 
     * @return Le prix de la carte, sous forme d'un nombre
     */
    @Column(name = "PRICE")
    public float getPrice() {
        return price;
    }
 
    /**
     * Met à jour le prix de la carte
     * 
     * @param price
     * 				Le nouveau prix de la carte
     */
    public void setPrice( float price ) {
    	if ( price <= 0 ) throw new RuntimeException( "Price must be positive" );
        this.price = price;
    }
   
    /**
     * Retourne la quantité de la carte
     * 
     * Ce champs est relié à la base de données grâce à une annotation
     * 
     * @return La quantité de la carte sous forme d'entier
     */
    @Column(name = "NBITEM")
    public int getNbItem() {
        return nbItem;
    }
 
    /**
     * Met à jour la quantité de la carte
     * 
     * @param nbItem
     * 					La nouvelle quantité de la carte
     */
    public void setNbItem( int nbItem ) {
    	if ( nbItem <= 0 ) throw new RuntimeException( "NbItem must be positive" );
        this.nbItem = nbItem;
    }

    /**
     * Retourne la quantité de la carte disponible
     *
     * Ce champs est relié à la base de données grâce à une annotation
     * 
     * @return La quantité de la carte disponible sous forme d'entier
     */
    @Column(name = "DISPONIBILITY")
    public int getNbDispo() {
        return nbDispo;
    }
 
    /**
     * Met à jour la quantité de la carte disponible
     * 
     * @param nbDispo
     * 					La nouvelle quantité de la carte disponible
     */
    public void setNbDispo( int nbDispo ) {
        this.nbDispo = nbDispo;
    }
    
    /**
     * Retourne la date de création ou de mise à jour de la carte
     * 
     * Ce champs est relié à la base de données grâce à une annotation
     *
     * @return La date de création ou de mise à jour de la carte, sous forme de date
     */
    @Column(name = "DATECREATION")
	public Date getDate() {
		return date;
	}

    /**
     * Met à jour la date de création ou de mise à jour de la carte
     * 
     * @param date
     * 				La nouvelle date de la carte
     */
	public void setDate( Date date ) {
		this.date = date;
	}
    
    /**
     * function hashCode ...
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
 
    /**
     * function equals ...
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Card))
            return false;
        Card other = (Card) obj;
        if (id != other.id)
            return false;
        return true;
    }
 
    /**
     * Retourne un String contenant toutes les informations relatives à cet objet
     */
    @Override
    public String toString() {
        return "Card [id=" + id + ", nameFr=" + nameFr + ", nameEn=" + nameEn
                + ", type=" + type + ", edition=" + edition + ", manaCost=" + manaCost 
                + ", rarity=" + rarity + ", price=" + price + ", nbItem=" + nbItem +", nbDispo=" + nbDispo +", date=" + date + "]";
    }
}