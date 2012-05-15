package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket19EntityAction extends OPacket {

   public int a;
   public int b;


   public OPacket19EntityAction() {
      super();
   }

   public void a(DataInputStream var1) throws IOException {
      this.a = var1.readInt();
      this.b = var1.readByte();
   }

   public void a(DataOutputStream var1) throws IOException {
      var1.writeInt(this.a);
      var1.writeByte(this.b);
   }

   public void a(ONetHandler var1) {
      var1.a(this);
   }

   public int a() {
      return 5;
   }
}