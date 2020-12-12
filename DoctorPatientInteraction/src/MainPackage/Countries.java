package MainPackage;

public class Countries {
 
 public Countries(String code,String name, String continent,String region,int population, String capital )
        {      
          this.setCode(code);
          this.setName(name);
          this.setContinent(continent);
          this.setRegion(region);
          this.setPopulation(population);
          this.setCapital(capital);
        }
 
 public Countries() {
  
 }

    private String code;
    private String name;
    private String continent;
    private String region;
    private int population;   
    private String capital;
    private String message;
    private String emailid;
    private String price;
    private String description;
    
    public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getIngrediants() {
		return ingrediants;
	}

	public void setIngrediants(String ingrediants) {
		this.ingrediants = ingrediants;
	}

	private String subcategory;
    private String ingrediants;
      
    public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCode(String code) {
  this.code = code;
 }
 public String getCode() {
  return code;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getName() {
  return name;
 }

 public void setContinent(String continent) {
  this.continent = continent;
 }

 public String getContinent() {
  return continent;
 }

 public void setRegion(String region) {
  this.region = region;
 }

 public String getRegion() {
  return region;
 }

 
 public void setPopulation(int population) {
  this.population = population;
 }

 public int getPopulation() {
  return population;
 }

 
 public void setCapital(String capital) {
  this.capital = capital;
 }

 public String getCapital() {
  return capital;
 }
}