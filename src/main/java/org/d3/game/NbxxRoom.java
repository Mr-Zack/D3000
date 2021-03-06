package org.d3.game;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.d3.core.BaseRoom;
import org.d3.game.bean.Monster;
import org.d3.game.bean.Player;
import org.d3.net.packet.Packet;
import org.d3.net.packet.Packets;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class NbxxRoom extends BaseRoom {
	
	private Set<Monster> monsters;
	private ConcurrentMap<String, Boolean> seats;
	private static final int ROOM_SIZE = 4;
	private static String SEAT = "SEAT";

	public NbxxRoom(String id, String name) {
		super(id, name);
		monsters = Sets.newHashSet();
		
		seats = Maps.newConcurrentMap();
		for(int i = 1; i <= ROOM_SIZE; i++){
			seats.put(SEAT + i, false);
		}
	}
	
	private ScheduledFuture future;
	
	public void startGame() {
//		sendMassage(Packets.newPacket(Packets.ROOM, Packets.ROOM_START_GAME, null));
//		
//		future = scheduledService.scheduleAtFixedRate(new Runnable() {
//			public void run() {
//				Monster m = new Monster();
//				monsters.add(m);
//				Packet pkt = Packets.newPacket(
//						Packets.ROOM,
//						Packets.ROOM_MAKE_MONSTER,
//						"ALL", m);
//				broadcast(pkt);
//			}
//		}, 5, 3, TimeUnit.SECONDS);
		
	}
	
	public void stopGame(){
		if(future != null){
			future.cancel(false);
		}
		monsters.clear();
	}
	
	public Monster getMonster(String id){
		for(Monster m: monsters){
			if(id.equals(m.getId())){
				return m;
			}
		}
		return null;
	}

	@Override
	protected int getRoomSize() {
		return ROOM_SIZE;
	}

	@Override
	protected void onLeaveRoom(Player player) {
		seats.put(SEAT + player.getSeat(), false);
	}
	@Override
	public int freeSeat(){
//		Thread.dumpStack();
		for(int i = 1; i <= seats.size(); i++){
			if(!seats.get(SEAT + i)){
				seats.put(SEAT + i, true);
//				System.out.println(i);
				return i;
			}
		}
		return -1;
	}

	@Override
	public Collection<Player> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}
}
