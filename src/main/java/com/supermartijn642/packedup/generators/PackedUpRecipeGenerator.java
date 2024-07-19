package com.supermartijn642.packedup.generators;

import com.supermartijn642.core.data.condition.ResourceCondition;
import com.supermartijn642.core.data.condition.TagPopulatedResourceCondition;
import com.supermartijn642.core.generator.RecipeGenerator;
import com.supermartijn642.core.generator.ResourceCache;
import com.supermartijn642.core.registry.Registries;
import com.supermartijn642.packedup.BackpackRecipeCondition;
import com.supermartijn642.packedup.BackpackType;
import com.supermartijn642.packedup.BackpackUpgradeRecipe;
import com.supermartijn642.packedup.PackedUp;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;

/**
 * Created 14/11/2022 by SuperMartijn642
 */
public class PackedUpRecipeGenerator extends RecipeGenerator {

    public PackedUpRecipeGenerator(ResourceCache cache){
        super("packedup", cache);
    }

    @Override
    public void generate(){
        // Basic
        ResourceCondition basicEnabled = new BackpackRecipeCondition(BackpackType.BASIC);
        this.shaped("basic_from_chest", PackedUp.basicbackpack)
            .pattern("ABA")
            .pattern("CDC")
            .pattern("CCC")
            .input('A', Tags.Items.RODS_WOODEN)
            .input('B', Tags.Items.STRINGS)
            .input('C', Tags.Items.LEATHERS)
            .input('D', Tags.Items.CHESTS_WOODEN)
            .unlockedBy(Tags.Items.CHESTS_WOODEN)
            .condition(basicEnabled);

        // Iron
        ResourceCondition ironEnabled = new BackpackRecipeCondition(BackpackType.IRON);
        this.shaped("iron_from_chest", PackedUp.ironbackpack)
            .pattern("ABA")
            .pattern("CDC")
            .pattern("CCC")
            .input('A', Tags.Items.LEATHERS)
            .input('B', Tags.Items.STRINGS)
            .input('C', Tags.Items.INGOTS_IRON)
            .input('D', Tags.Items.CHESTS_WOODEN)
            .unlockedBy(Tags.Items.CHESTS_WOODEN)
            .condition(basicEnabled.negate())
            .condition(ironEnabled);
        this.shaped("iron_from_basic", PackedUp.ironbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', Tags.Items.INGOTS_IRON)
            .input('B', Tags.Items.LEATHERS)
            .input('C', PackedUp.basicbackpack)
            .unlockedBy(PackedUp.basicbackpack)
            .condition(basicEnabled)
            .condition(ironEnabled);

        // Copper
        ResourceCondition copperEnabled = new BackpackRecipeCondition(BackpackType.COPPER).and(new TagPopulatedResourceCondition(Registries.ITEMS, Tags.Items.INGOTS_COPPER.location()));
        this.shaped("copper_from_chest", PackedUp.copperbackpack)
            .pattern("ABA")
            .pattern("CDC")
            .pattern("CCC")
            .input('A', Tags.Items.LEATHERS)
            .input('B', Tags.Items.STRINGS)
            .input('C', Tags.Items.INGOTS_COPPER)
            .input('D', Tags.Items.CHESTS_WOODEN)
            .unlockedBy(Tags.Items.CHESTS_WOODEN)
            .condition(basicEnabled.negate())
            .condition(copperEnabled);
        this.shaped("copper_from_basic", PackedUp.copperbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', Tags.Items.INGOTS_COPPER)
            .input('B', Tags.Items.LEATHERS)
            .input('C', PackedUp.basicbackpack)
            .unlockedBy(PackedUp.basicbackpack)
            .condition(basicEnabled)
            .condition(copperEnabled);

        // Silver
        TagKey<Item> silverIngots = TagKey.create(Registries.ITEMS.getVanillaRegistry().key(), ResourceLocation.fromNamespaceAndPath("c", "ingots/silver"));
        ResourceCondition silverEnabled = new BackpackRecipeCondition(BackpackType.SILVER).and(new TagPopulatedResourceCondition(Registries.ITEMS, ResourceLocation.fromNamespaceAndPath("c", "ingots/silver")));
        this.shaped("silver_from_chest", PackedUp.silverbackpack)
            .pattern("ABA")
            .pattern("CDC")
            .pattern("CCC")
            .input('A', Tags.Items.LEATHERS)
            .input('B', Tags.Items.STRINGS)
            .input('C', silverIngots)
            .input('D', Tags.Items.CHESTS_WOODEN)
            .unlockedBy(Tags.Items.CHESTS_WOODEN)
            .condition(basicEnabled.negate())
            .condition(ironEnabled.negate())
            .condition(copperEnabled.negate())
            .condition(silverEnabled);
        this.shaped("silver_from_basic", PackedUp.silverbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', silverIngots)
            .input('B', Tags.Items.LEATHERS)
            .input('C', PackedUp.basicbackpack)
            .unlockedBy(PackedUp.basicbackpack)
            .condition(basicEnabled)
            .condition(ironEnabled.negate())
            .condition(copperEnabled.negate())
            .condition(silverEnabled);
        this.shaped("silver_from_iron", PackedUp.silverbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', silverIngots)
            .input('B', Tags.Items.INGOTS_IRON)
            .input('C', PackedUp.ironbackpack)
            .unlockedBy(PackedUp.ironbackpack)
            .condition(ironEnabled)
            .condition(silverEnabled);
        this.shaped("silver_from_copper", PackedUp.silverbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', silverIngots)
            .input('B', Tags.Items.INGOTS_COPPER)
            .input('C', PackedUp.copperbackpack)
            .unlockedBy(PackedUp.copperbackpack)
            .condition(copperEnabled)
            .condition(silverEnabled);

        // Gold
        ResourceCondition goldEnabled = new BackpackRecipeCondition(BackpackType.GOLD);
        this.shaped("gold_from_chest", PackedUp.goldbackpack)
            .pattern("ABA")
            .pattern("CDC")
            .pattern("CCC")
            .input('A', Tags.Items.LEATHERS)
            .input('B', Tags.Items.STRINGS)
            .input('C', Tags.Items.INGOTS_GOLD)
            .input('D', Tags.Items.CHESTS_WOODEN)
            .unlockedBy(Tags.Items.CHESTS_WOODEN)
            .condition(basicEnabled.negate())
            .condition(ironEnabled.negate())
            .condition(copperEnabled.negate())
            .condition(goldEnabled);
        this.shaped("gold_from_basic", PackedUp.goldbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', Tags.Items.INGOTS_GOLD)
            .input('B', Tags.Items.LEATHERS)
            .input('C', PackedUp.basicbackpack)
            .unlockedBy(PackedUp.basicbackpack)
            .condition(basicEnabled)
            .condition(ironEnabled.negate())
            .condition(copperEnabled.negate())
            .condition(goldEnabled);
        this.shaped("gold_from_iron", PackedUp.goldbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', Tags.Items.INGOTS_GOLD)
            .input('B', Tags.Items.INGOTS_IRON)
            .input('C', PackedUp.ironbackpack)
            .unlockedBy(PackedUp.ironbackpack)
            .condition(ironEnabled)
            .condition(goldEnabled);
        this.shaped("gold_from_copper", PackedUp.goldbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', Tags.Items.INGOTS_GOLD)
            .input('B', Tags.Items.INGOTS_COPPER)
            .input('C', PackedUp.copperbackpack)
            .unlockedBy(PackedUp.copperbackpack)
            .condition(copperEnabled)
            .condition(goldEnabled);

        // Diamond
        ResourceCondition diamondEnabled = new BackpackRecipeCondition(BackpackType.DIAMOND);
        this.shaped("diamond_from_chest", PackedUp.diamondbackpack)
            .pattern("ABA")
            .pattern("CDC")
            .pattern("CEC")
            .input('A', Tags.Items.LEATHERS)
            .input('B', Tags.Items.STRINGS)
            .input('C', Tags.Items.GEMS_DIAMOND)
            .input('D', Tags.Items.CHESTS_WOODEN)
            .input('E', Tags.Items.GLASS_BLOCKS_CHEAP)
            .unlockedBy(Tags.Items.CHESTS_WOODEN)
            .condition(basicEnabled.negate())
            .condition(ironEnabled.negate())
            .condition(copperEnabled.negate())
            .condition(silverEnabled.negate())
            .condition(goldEnabled.negate())
            .condition(diamondEnabled);
        this.shaped("diamond_from_basic", PackedUp.diamondbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("DDD")
            .input('A', Tags.Items.GEMS_DIAMOND)
            .input('B', Tags.Items.LEATHERS)
            .input('C', PackedUp.basicbackpack)
            .input('D', Tags.Items.GLASS_BLOCKS_CHEAP)
            .unlockedBy(PackedUp.basicbackpack)
            .condition(basicEnabled)
            .condition(ironEnabled.negate())
            .condition(copperEnabled.negate())
            .condition(silverEnabled.negate())
            .condition(goldEnabled.negate())
            .condition(diamondEnabled);
        this.shaped("diamond_from_iron", PackedUp.diamondbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("DDD")
            .input('A', Tags.Items.GEMS_DIAMOND)
            .input('B', Tags.Items.INGOTS_IRON)
            .input('C', PackedUp.ironbackpack)
            .input('D', Tags.Items.GLASS_BLOCKS_CHEAP)
            .unlockedBy(PackedUp.ironbackpack)
            .condition(ironEnabled)
            .condition(silverEnabled.negate())
            .condition(goldEnabled.negate())
            .condition(diamondEnabled);
        this.shaped("diamond_from_copper", PackedUp.diamondbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("DDD")
            .input('A', Tags.Items.GEMS_DIAMOND)
            .input('B', Tags.Items.INGOTS_COPPER)
            .input('C', PackedUp.copperbackpack)
            .input('D', Tags.Items.GLASS_BLOCKS_CHEAP)
            .unlockedBy(PackedUp.copperbackpack)
            .condition(copperEnabled)
            .condition(silverEnabled.negate())
            .condition(goldEnabled.negate())
            .condition(diamondEnabled);
        this.shaped("diamond_from_silver", PackedUp.diamondbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("DDD")
            .input('A', Tags.Items.GEMS_DIAMOND)
            .input('B', silverIngots)
            .input('C', PackedUp.silverbackpack)
            .input('D', Tags.Items.GLASS_BLOCKS_CHEAP)
            .unlockedBy(PackedUp.silverbackpack)
            .condition(silverEnabled)
            .condition(diamondEnabled);
        this.shaped("diamond_from_gold", PackedUp.diamondbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("DDD")
            .input('A', Tags.Items.GEMS_DIAMOND)
            .input('B', Tags.Items.INGOTS_GOLD)
            .input('C', PackedUp.goldbackpack)
            .input('D', Tags.Items.GLASS_BLOCKS_CHEAP)
            .unlockedBy(PackedUp.goldbackpack)
            .condition(goldEnabled)
            .condition(diamondEnabled);

        // Obsidian
        ResourceCondition obsidianEnabled = new BackpackRecipeCondition(BackpackType.OBSIDIAN);
        this.shaped("obsidian_from_chest", PackedUp.obsidianbackpack)
            .pattern("ABA")
            .pattern("CDC")
            .pattern("CCC")
            .input('A', Tags.Items.LEATHERS)
            .input('B', Tags.Items.STRINGS)
            .input('C', Tags.Items.OBSIDIANS)
            .input('D', Tags.Items.CHESTS_WOODEN)
            .unlockedBy(Tags.Items.CHESTS_WOODEN)
            .condition(basicEnabled.negate())
            .condition(ironEnabled.negate())
            .condition(copperEnabled.negate())
            .condition(silverEnabled.negate())
            .condition(goldEnabled.negate())
            .condition(diamondEnabled.negate())
            .condition(obsidianEnabled);
        this.shaped("obsidian_from_basic", PackedUp.obsidianbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', Tags.Items.OBSIDIANS)
            .input('B', Tags.Items.LEATHERS)
            .input('C', PackedUp.basicbackpack)
            .unlockedBy(PackedUp.basicbackpack)
            .condition(basicEnabled)
            .condition(ironEnabled.negate())
            .condition(copperEnabled.negate())
            .condition(silverEnabled.negate())
            .condition(goldEnabled.negate())
            .condition(diamondEnabled.negate())
            .condition(obsidianEnabled);
        this.shaped("obsidian_from_iron", PackedUp.obsidianbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', Tags.Items.OBSIDIANS)
            .input('B', Tags.Items.INGOTS_IRON)
            .input('C', PackedUp.ironbackpack)
            .unlockedBy(PackedUp.ironbackpack)
            .condition(ironEnabled)
            .condition(silverEnabled.negate())
            .condition(goldEnabled.negate())
            .condition(diamondEnabled.negate())
            .condition(obsidianEnabled);
        this.shaped("obsidian_from_copper", PackedUp.obsidianbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', Tags.Items.OBSIDIANS)
            .input('B', Tags.Items.INGOTS_COPPER)
            .input('C', PackedUp.copperbackpack)
            .unlockedBy(PackedUp.copperbackpack)
            .condition(copperEnabled)
            .condition(silverEnabled.negate())
            .condition(goldEnabled.negate())
            .condition(diamondEnabled.negate())
            .condition(obsidianEnabled);
        this.shaped("obsidian_from_silver", PackedUp.obsidianbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', Tags.Items.OBSIDIANS)
            .input('B', silverIngots)
            .input('C', PackedUp.silverbackpack)
            .unlockedBy(PackedUp.silverbackpack)
            .condition(silverEnabled)
            .condition(diamondEnabled.negate())
            .condition(obsidianEnabled);
        this.shaped("obsidian_from_gold", PackedUp.obsidianbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', Tags.Items.OBSIDIANS)
            .input('B', Tags.Items.INGOTS_GOLD)
            .input('C', PackedUp.goldbackpack)
            .unlockedBy(PackedUp.goldbackpack)
            .condition(goldEnabled)
            .condition(diamondEnabled.negate())
            .condition(obsidianEnabled);
        this.shaped("obsidian_from_diamond", PackedUp.obsidianbackpack)
            .customSerializer(BackpackUpgradeRecipe.SERIALIZER)
            .pattern("ABA")
            .pattern("ACA")
            .pattern("AAA")
            .input('A', Tags.Items.OBSIDIANS)
            .input('B', Tags.Items.GEMS_DIAMOND)
            .input('C', PackedUp.diamondbackpack)
            .unlockedBy(PackedUp.diamondbackpack)
            .condition(diamondEnabled)
            .condition(obsidianEnabled);
    }
}
