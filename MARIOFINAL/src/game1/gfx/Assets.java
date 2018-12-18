package game1.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	final static int width = 16;
	final static int height = 16;
	public static BufferedImage dead, backM, back1, back2, back3, player_head_left_gun, bullet, brick, grass, nullbox, mario, player_head_right, player_head_left, back, floor, player_jump_right, player_jump_left, guard_head_right, guard_head_left, player_head_right_gun;
	public static BufferedImage[] over_boss_left;
	public static BufferedImage[] guard_left;
	public static BufferedImage[] guard_right;
	public static BufferedImage[] over_boss_right;
	public static BufferedImage[] player_right;
	public static BufferedImage[] player_left;
	public static BufferedImage[] nisit_left;
	public static BufferedImage[] nisit_right;
	public static BufferedImage[] mysBox;
 public static void init() {
	 SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/textures/Tileset.png"));
	 SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/smb_mario_sheet.png"));
	 SpriteSheet sheet3 = new SpriteSheet(ImageLoader.loadImage("/textures/guard.png"));
	 
	 mysBox = new BufferedImage[2];
	 mysBox[0] = sheet1.crop(width*24, 0, width, height);
	 mysBox[1] = sheet1.crop(400, 0, width, height);
	 
	 bullet = sheet2.crop(237, 28, 3, 1);
	 player_head_right_gun = sheet2.crop(211, 29, 18, 15);
	 player_head_left_gun = sheet2.crop(177, 29, 18, 15);
	 
	 guard_left = new BufferedImage[4];
	 guard_left[0] = sheet3.crop(99, 0, 16, 15);
	 guard_left[1] = sheet3.crop(132, 0, 16, 15);
	 guard_left[2] = sheet3.crop(161, 0, 16, 15);
	 guard_left[3] = sheet3.crop(132, 0, 16, 15);
	 
	 guard_right = new BufferedImage[4];
	 guard_right[0] = sheet3.crop(241, 0, 16, 15);
	 guard_right[1] = sheet3.crop(272, 0, 16, 15);
	 guard_right[2] = sheet3.crop(300, 0, 16, 15);
	 guard_right[3] = sheet3.crop(272, 0, 16, 15);
	 
	 over_boss_left = new BufferedImage[4];
	 over_boss_left[0] = sheet3.crop(95, 52, 16, 32);
	 over_boss_left[1] = sheet3.crop(125, 52, 16, 32);
	 over_boss_left[2] = sheet3.crop(154, 52, 16, 32);
	 over_boss_left[3] = sheet3.crop(125, 52, 16, 32);
	 
	 over_boss_right = new BufferedImage[4];
	 over_boss_right[0] = sheet3.crop(239, 52, 16, 32);
	 over_boss_right[1] = sheet3.crop(270, 52, 16, 32);
	 over_boss_right[2] = sheet3.crop(299, 52, 16, 32);
	 over_boss_right[3] = sheet3.crop(270, 52, 16, 32);
	 
	 nisit_right = new BufferedImage[4];
	 nisit_right[0] = sheet3.crop(243, 25, 16, 15);
	 nisit_right[1] = sheet3.crop(272, 25, 16, 15);
	 nisit_right[2] = sheet3.crop(303, 25, 16, 15);
	 nisit_right[3] = sheet3.crop(272, 25, 16, 15);
	 
	 nisit_left = new BufferedImage[4];
	 nisit_left[0] = sheet3.crop(92, 25, 16, 15);
	 nisit_left[1] = sheet3.crop(123, 25, 16, 15);
	 nisit_left[2] = sheet3.crop(153, 25, 16, 15);
	 nisit_left[3] = sheet3.crop(123, 25, 16, 15);
	 
	 player_right = new BufferedImage[4];
	 player_right[0] = sheet2.crop(239, 0, 16, 15);
	 player_right[1] = sheet2.crop(271, 0, 16, 15);
	 player_right[2] = sheet2.crop(300, 0, 16, 15);
	 player_right[3] = sheet2.crop(271, 0, 16, 15);
	 
	 player_left = new BufferedImage[4];
	 player_left[0] = sheet2.crop(150, 0, 16, 15);
	 player_left[1] = sheet2.crop(121, 0, 16, 15);
	 player_left[2] = sheet2.crop(89, 0, 16, 15);
	 player_left[3] = sheet2.crop(121, 0, 16, 15);
	 
	 
	 guard_head_right = sheet3.crop(211, 0, 16, 15);
	 guard_head_left = sheet3.crop(181, 0, 16, 15);
	 player_head_right = sheet2.crop(210, 0, 16, 16);
	 player_head_left = sheet2.crop(181, 0, 16, 16);
	 player_jump_right = sheet2.crop(360, 0, 16, 16);
	 player_jump_left = sheet2.crop(29, 0, 16, 16);
	 back = ImageLoader.loadImage("/textures/bg.png");
	 back1 = ImageLoader.loadImage("/textures/bg1.png");
	 back2 = ImageLoader.loadImage("/textures/bg2.png");
	 back3 = ImageLoader.loadImage("/textures/bg3.png");
	 backM = ImageLoader.loadImage("/textures/menu.png");
	 brick = sheet1.crop(width, 0, width, height);
	 nullbox = sheet1.crop(width*3, 0, width, height);
	 floor = sheet1.crop( 0, 0, width, height);
 }
}
