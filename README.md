c2d-engine
==========

move from https://code.google.com/p/c2d-engine/

c2d is a game engine, base on [https://github.com/libgdx/libgdx libgdx].  Aim to easy use of the the libgdx api .I design c2d at my free time so if you have any ideas please give me feedback . 
#### Some of the characteristics
C2d built a readable and expandable API . 
 
* Resources and loading
 - Alias Resource Manager any resources my be had one or more alias so you can easy change the resource but not the codes.
 - Custom resources load , you can change the subfix and protected your resources easily.
 - Custom game loadding screen
* Event and Screen Switch
 - EventManager ,fire the regiestered event anytime.
 - TransitionScreen the animations of screen switch
* Graphics
 - Animation Sprite support loop and LoopWithTime? mode.
 - Parallax Layer the Parallax Layer ,you can custom the layer's content via ParallaxLayerDrawable?
 - Surfaces support opengles1.x and 2.x .
* Pixmap HelperModifying textures using libGDX Pixmap in runtime
* Box2d
 - Load the world from json the json file is designed with c2d-tools


### Demo
Some games use c2d-engine.

Fool Dig
https://play.google.com/store/apps/details?id=info.u250.digs

![screenshort](https://lh3.ggpht.com/nLj_-YjGajv0_h4jhS_8hn05klB0QCjqjzfmoa95TABV-kgvFeR5Gaf1M5iyimAj1uE=h310)

My Dragon
https://play.google.com/store/apps/details?id=com.joyboat6.iland

![screenshort](https://lh3.ggpht.com/CyEBc6T6CPOqCHUNVq8SSTxvmuN8TiTH-ugkXrJsnwwCbOU7vpEBi79C4dlq2QTzCVY=h310)
