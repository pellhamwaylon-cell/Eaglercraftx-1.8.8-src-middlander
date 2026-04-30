package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.CombatTracker;

public class S42PacketCombatEvent implements Packet<INetHandlerPlayClient> {
	public S42PacketCombatEvent.Event eventType;
	public int field_179774_b;
	public int field_179775_c;
	public int field_179772_d;
	public String deathMessage;

	public S42PacketCombatEvent() {
	}

	public S42PacketCombatEvent(CombatTracker combatTrackerIn, S42PacketCombatEvent.Event combatEventType) {
		this.eventType = combatEventType;
		EntityLivingBase entitylivingbase = combatTrackerIn.func_94550_c();
		switch (combatEventType) {
		case END_COMBAT:
			this.field_179772_d = combatTrackerIn.func_180134_f();
			this.field_179775_c = entitylivingbase == null ? -1 : entitylivingbase.getEntityId();
			break;
		case ENTITY_DIED:
			this.field_179774_b = combatTrackerIn.getFighter().getEntityId();
			this.field_179775_c = entitylivingbase == null ? -1 : entitylivingbase.getEntityId();
			this.deathMessage = combatTrackerIn.getDeathMessage().getUnformattedText();
		}

	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.eventType = (S42PacketCombatEvent.Event) parPacketBuffer.readEnumValue(S42PacketCombatEvent.Event.class);
		if (this.eventType == S42PacketCombatEvent.Event.END_COMBAT) {
			this.field_179772_d = parPacketBuffer.readVarIntFromBuffer();
			this.field_179775_c = parPacketBuffer.readInt();
		} else if (this.eventType == S42PacketCombatEvent.Event.ENTITY_DIED) {
			this.field_179774_b = parPacketBuffer.readVarIntFromBuffer();
			this.field_179775_c = parPacketBuffer.readInt();
			this.deathMessage = parPacketBuffer.readStringFromBuffer(32767);
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeEnumValue(this.eventType);
		if (this.eventType == S42PacketCombatEvent.Event.END_COMBAT) {
			parPacketBuffer.writeVarIntToBuffer(this.field_179772_d);
			parPacketBuffer.writeInt(this.field_179775_c);
		} else if (this.eventType == S42PacketCombatEvent.Event.ENTITY_DIED) {
			parPacketBuffer.writeVarIntToBuffer(this.field_179774_b);
			parPacketBuffer.writeInt(this.field_179775_c);
			parPacketBuffer.writeString(this.deathMessage);
		}

	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleCombatEvent(this);
	}

	public static enum Event {
		ENTER_COMBAT, END_COMBAT, ENTITY_DIED;
	}
}
