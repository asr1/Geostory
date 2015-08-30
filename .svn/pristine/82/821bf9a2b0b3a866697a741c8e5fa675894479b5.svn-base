<?php
/**
 * Created by PhpStorm.
 * User: admin
 * Date: 10/20/2014
 * Time: 4:57 PM
 */

namespace GeoStory\Controller\Plugin;


class GeoMath {

    private $radius = 6378100; // radius of earth in meters

    public function getMetersBetweenGPS($lat1,$lng1,$lat2,$lng2){
        $latDist = $lat1 - $lat2;
        $lngDist = $lng1 - $lng2;
        $sinLatDist = sin(deg2rad($latDist));
        $sinLngDist = sin(deg2rad($lngDist));
        $cosLat1 = cos(deg2rad($lat1));
        $cosLat2 = cos(deg2rad($lat2));
        //todo
    }
} 