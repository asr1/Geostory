<?php
/**
 * Created by PhpStorm.
 * User: admin
 * Date: 10/11/2014
 * Time: 4:31 PM
 */

namespace GeoStory\Entity;


use Doctrine\ORM\Mapping as ORM;
use DateTime;

//use Zend\InputFilter\InputFilter;
//use Zend\InputFilter\Factory as InputFactory;
//use Zend\InputFilter\InputFilterAwareInterface;
//use Zend\InputFilter\InputFilterInterface;


/**
 * A GeoStory story
 *
 * @ORM\Entity
 * @ORM\Table(name="gps_location")
 */
class GpsLocation {
    /**
     * @ORM\Id
     * @ORM\Column(type="integer");
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;

    /**
     * @ORM\OneToOne(targetEntity="Story", mappedBy="gpsLocation")
     */
    private $story;


    /**
     * @ORM\Column(type="float");
     */
    private $longitude;

    /**
     * @ORM\Column(type="float");
     */
    private $latitude;

    function __construct($latitude, $longitude, $story)
    {
        $this->latitude = $latitude;
        $this->longitude = $longitude;
        $this->story = $story;
    }


    /**
     * @return mixed
     */
    public function getLatitude()
    {
        return $this->latitude;
    }

    /**
     * @param mixed $latitude
     */
    public function setLatitude($latitude)
    {
        $this->latitude = $latitude;
    }

    /**
     * @return mixed
     */
    public function getLongitude()
    {
        return $this->longitude;
    }

    /**
     * @param mixed $longitude
     */
    public function setLongitude($longitude)
    {
        $this->longitude = $longitude;
    }

    /**
     * @return mixed
     */
    public function getStory()
    {
        return $this->story;
    }

    /**
     * @param mixed $story
     */
    public function setStory($story)
    {
        $this->story = $story;
    }




}