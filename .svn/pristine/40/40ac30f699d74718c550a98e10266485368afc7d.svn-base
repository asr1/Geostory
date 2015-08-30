<?php
/**
 * Created by PhpStorm.
 * User: admin
 * Date: 10/20/2014
 * Time: 4:18 PM
 */

namespace GeoStory\Entity\Repository;
use Doctrine\ORM\EntityRepository;


class StoryRepository  extends EntityRepository {

    private static $MaxDistanceMeters = 200;
    public function getAllStories()
    {
        return $this->createQueryBuilder('s')
            ->where('s.deleted = FALSE')
            ->getQuery()
            ->getResult();
    }

    public function getStoriesByDistance($lat,$lng,$dist){
        $latLowest = $lat - $dist/69/1000; //limit search within 1 degree
        $latHighest = $lat + $dist/69/1000; //limit search within 1 degree
        $lngLowest = $lng - $dist/abs(cos(deg2rad($lat))*69)/1000;
        $lngHighest = $lng + $dist/abs(cos(deg2rad($lat))*69)/1000;
       // $this->_em->getConfiguration()->registerString
        return $this->_em->createQuery("
            SELECT g,s,DISTANCE(g.latitude,g.longitude,$lat,$lng) as distance
            FROM GeoStory\\Entity\\Story s
            JOIN s.gpsLocation g
            WHERE s.deleted = FALSE
            AND g.latitude BETWEEN $latLowest AND $latHighest
            AND g.longitude BETWEEN $lngLowest AND $lngHighest
        "
        )->getArrayResult();

    }
}
