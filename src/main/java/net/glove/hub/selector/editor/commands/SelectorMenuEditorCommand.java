package net.glove.hub.selector.editor.commands;

import com.google.common.collect.Sets;
import net.glove.global.utils.command.BaseCommand;
import net.glove.global.utils.command.annotation.CommandExecutor;
import net.glove.global.utils.command.annotation.CommandInfo;
import net.glove.hub.selector.editor.commands.subCommands.CreateMenu;
import net.glove.hub.selector.editor.commands.subCommands.EditMenu;
import net.glove.hub.selector.editor.commands.subCommands.List;
import net.glove.hub.selector.editor.commands.subCommands.RemoveMenu;
import org.bukkit.entity.Player;

import java.util.Set;

@CommandInfo({"selectormenueditor", "sme"})
public class SelectorMenuEditorCommand implements BaseCommand {

    @Override
    public String getPermissionNode() {
        return "glove.hub.selectormenueditor";
    }

    @CommandExecutor
    public void execute(Player player){
        player.sendMessage("&b&lServer Selector Editor help");
        player.sendMessage("&7/selectormenueditor createmenu (Title)");
        player.sendMessage("&7/selectormenueditor editMenu (ID)");
        player.sendMessage("&7/selectormenueditor removeMenu (ID)");
        player.sendMessage("&7/selectormenueditor list");
    }

    @Override
    public Set<BaseCommand> getSubCommands() {
        return Sets.newHashSet(
            new CreateMenu(),
            new EditMenu(),
            new List(),
            new RemoveMenu()
        );
    }
}