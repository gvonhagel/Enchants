package co.reasondev.enchant.enchantment.custom;

import co.reasondev.enchant.enchantment.CustomEnchant;
import com.codingforcookies.armorequip.ArmorEquipEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Agility extends CustomEnchant {

    public Agility(int id, String name, int maxLevel) {
        super(id, name, maxLevel);
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_FEET;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return enchantment instanceof Adrenaline;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return itemStack.getType().toString().contains("_BOOTS");
    }

    @Override
    public void handleItemChange(PlayerItemHeldEvent e) {
    }

    @Override
    public void handleItemEquip(ArmorEquipEvent e) {
        if (hasEnchant(e.getOldArmorPiece())) {
            e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
        }
        if (hasEnchant(e.getNewArmorPiece())) {
            int level = e.getNewArmorPiece().getEnchantments().get(this);
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, level - 1));
        }
    }

    @Override
    public void handleEntityDamageByEntity(EntityDamageByEntityEvent e) {
    }

    @Override
    public void handleEntityDeath(EntityDeathEvent e) {

    }

    @Override
    public void handleBlockBreak(BlockBreakEvent e) {
    }
}
