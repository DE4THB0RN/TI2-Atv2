package atv2;

public class Chefia {

	public static void main(String[] args) 
	{
		DAO dao = new DAO();
		dao.conectar();
		int x = 0;
		int num = 0;
		String str;
		Boolean promo;
		Wishlist novo = new Wishlist();
		Wishlist wish[] = null;
		while(x != 6) 
		{
			System.out.println("========== Options ==========");
			System.out.println("1) Listar itens da wishlist");
			System.out.println("2) Listar itens em promo");
			System.out.println("3) Inserir item na wishlist");
			System.out.println("4) Excluir item da wishlist");
			System.out.println("5) Atualizar item da wishlist");
			System.out.println("6) Sair");
			x = MyIO.readInt();
			switch(x)
			{
				case 1:
					
					
					wish = dao.getItems();
					for(int i = 0; i < wish.length; i++) {
						System.out.println(wish[i].toString());
					}
					break;
				case 2:
					
						wish = dao.getJogosPromo();
						for(int i = 0; i < wish.length; i++) {
							System.out.println(wish[i].toString());
						}
						break;
					
					
				case 3:
					
					str = MyIO.readLine("Insira o nome do jogo: ");
					novo.setNome(str);
					
					num = MyIO.readInt("Insira a porcentagem do desconto: ");
					novo.setPrcnt(num);
					
					promo = MyIO.readBoolean("O jogo esta em promocao: true or false: ");
					novo.setEstado(promo);
					
					num = MyIO.readInt("Insira a id do jogo: ");
					novo.setId(num);
					
					
					
					dao.inserirItem(novo);
					
					break;
				
				case 4:
					
					num = MyIO.readInt("Insira a id do jogo a ser excluido");
					
					dao.excluirItem(num);
					break;
					
				case 5:
					str = MyIO.readLine("Insira o novo nome do jogo: ");
					novo.setNome(str);
					
					num = MyIO.readInt("Insira a nova porcentagem do desconto: ");
					novo.setPrcnt(num);
					
					promo = MyIO.readBoolean("O jogo esta em promoção: true or false: ");
					novo.setEstado(promo);
					
					num = MyIO.readInt("Insira a id do jogo a ser atualizado: ");
					novo.setId(num);
					
					dao.atualizarItem(novo);
					
					break;
				
				case 6: 
					
					break;
			
				default:
					System.out.println("Valor invalido");
			}
		}
		
		dao.close();
	}

}
