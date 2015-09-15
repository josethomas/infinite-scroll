#!/bin/bash
c=0

while [ $c -lt 12 ]; do
  x='db.item.insert({"title":"title'$c'","blurb":"blurb'$c'","author":"auth'$c'","thumbUrl":"http://40.media.tumblr.com/6a70ba366ee157278515ad5f20284b1d/tumblr_ml6o2uXHzL1s4mpljo1_1280.jpg","detailsUrl":"https://www.google.com"});'
  printf "$x\nsleep(2000);\n"
  
  ((c++))
done

