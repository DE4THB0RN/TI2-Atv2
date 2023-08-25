package atv2;

public class Wishlist 
{
	private int prcnt;
	private String nome;
	private Boolean estado;
	private int id;
	
	public Wishlist() 
	{
		prcnt = 0;
		nome = "";
		estado = false;
	}
	
	public Wishlist(int prcnt,String nome,Boolean estado,int id) 
	{
		this.prcnt = prcnt;
		this.nome = nome;
		this.estado = estado;
		this.id = id;
	}
	
	public int getPrcnt() 
	{
		return prcnt;
	}
	
	public String getNome() 
	{
		return nome;
	}
	
	public Boolean getEstado() 
	{
		return estado;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setPrcnt(int prcnt) 
	{
		this.prcnt = prcnt;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public void setEstado(Boolean estado) 
	{
		this.estado = estado;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	@Override
	public String toString() 
	{
		return "Nome do jogo: " + nome + "\n Esta em promo: " + estado + "\n Redução do valor: " + prcnt + "% \n Id do jogo: " + id;
	}
	
}
