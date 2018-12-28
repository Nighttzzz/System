package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import rush.configuracoes.Mensagens;
import rush.entidades.Kits;

public class ComandoCriarkit implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
			
		// Verificando se o sender � um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode); 
			return true;
		}
			
		// Verificando se o player digitou o n�mero de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.CriarKit_Comando_Incorreto);
			return true;
		}
			 
		// Pegando o argumento e verificando se o kit j� existe
		String kit = args[0].toLowerCase();
		if (Kits.contains(kit)) {
			s.sendMessage(Mensagens.Kit_Ja_Existe.replace("%kit-id%", kit));
			return true;
		}
			
		// Verificando se o nome do kit n�o � maior que o permitido
		if (args[0].length() > 10) {
			s.sendMessage("�cO id do kit n�o pode conter mais de 10 caracteres.");
			return true;
		}
			
		// Pegando o player abrindo um inventario... o resto do processo � feito pela classe KitsListener
		Player p = (Player)s;
		Inventory inv = Bukkit.getServer().createInventory(p, 36, "Kit �2�n" + kit);
		p.openInventory(inv);
		return true;
	}
}