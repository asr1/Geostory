<?php
/**
 * Created by PhpStorm.
 * User: admin
 * Date: 10/1/2014
 * Time: 8:03 PM
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
 * @ORM\Entity(repositoryClass="GeoStory\Entity\Repository\StoryRepository")
 * @ORM\Table(name="story")
 *
 */
class Story {


/********************************* START ATTRIBUTES *********************************/
    /**
    * @ORM\Id
    * @ORM\Column(type="integer");
    * @ORM\GeneratedValue(strategy="AUTO")
    */
    protected $id;


    /**
     * @ORM\ManyToOne(targetEntity="User", inversedBy="stories")
     * @ORM\JoinColumn(name="author_id", referencedColumnName="user_id")
     */
    protected $author;

    /**
     * @ORM\OneToOne(targetEntity="GpsLocation")
     * @ORM\JoinColumn(name="gpslocation_id", referencedColumnName="id")
     */
     protected $gpsLocation;
//
//    /**
//     * @ORM\OneToMany(targetEntity="Report", mappedBy="story")
//     **/
//    protected $reports;
//
    /**
     * @ORM\OneToMany(targetEntity="View", mappedBy="story")
     **/
    protected $views;
//
//
//    /**
//     * @ORM\OneToMany(targetEntity="StoryComment", mappedBy="story")
//     **/
//    protected $storyComments;


    /**
     * @ORM\Column(type="string")
     */
    protected $title;

    /**
     * @ORM\Column(type="string", nullable=true)
     */
    protected $photoFile;

    /**
     * @ORM\Column(type="boolean")
     */
    protected $deleted;

    /**
     * @ORM\Column(type="float")
     */
    protected $cachedRating;


    /**
     * @ORM\Column(type="text")
     */
    protected $storyText;

    /**
     * @ORM\Column(type="datetime")
     */
    protected $dateCreated;


    /**

     * @ORM\Column(type="integer");
     */
    protected $cachedNumberOfViews;


    /******************************** END ATTRIBUTES *********************************/


    function __construct($user, $title, $storyText,$imageFile) {
        $this->deleted = false;
        $this->dateCreated = new DateTime('NOW');
        $this->cachedRating = 10.0;
        $this->author = $user;
        $this->title = $title;
        $this->storyText = $storyText;
        $this->photoFile = $imageFile;
        $this->cachedNumberOfViews = 0;
    }


    function adjustRating()
    {
        //todo do sql instead
        $totalRating = 0.1;
        $count = 0;
        foreach ($this->views as $view) {
            if ($view->hasRating()) {
                $totalRating += $view->getRating();
                $count++;
            }
        }
        $this->cachedRating = $totalRating / $count;
    }
/********************************* START GETTERS/SETTERS *********************************/

    /**
     * @return array
     */
    public function toArray()
    {

        return array(
            'user' => $this->author->getEmail(),
            'userId' =>  $this->author->getId(),
            'id' => $this->id,
            'title' => $this->title,
            'storyText' => $this->storyText,
            'dateCreated' => $this->dateCreated->format('d-m-y'),
            'rating' => $this->cachedRating,
            'imageFile' =>  $this->photoFile,
            'longitude' => $this->getGpsLocation()->getLongitude(),
            'latitude' => $this->getGpsLocation()->getLatitude(),
            'numOfView' => $this->cachedNumberOfViews,

        );
    }

    /**
     * @return mixed
     */
    public function getAuthor()
    {
        return $this->author;
    }

    public function incrementViewCount()
    {
        $this->cachedNumberOfViews++;
    }
    /**
     * @param mixed $author
     */
    public function setAuthor($author)
    {
        $this->author = $author;
    }

    /**
     * @return mixed
     */
    public function getCachedNumberOfViews()
    {
        return $this->cachedNumberOfViews;
    }

    /**
     * @param mixed $cachedNumberOfViews
     */
    public function setCachedNumberOfViews($cachedNumberOfViews)
    {
        $this->cachedNumberOfViews = $cachedNumberOfViews;
    }

    /**
     * @return mixed
     */
    public function getCachedRating()
    {
        return $this->cachedRating;
    }

    /**
     * @param mixed $cachedRating
     */
    public function setCachedRating($cachedRating)
    {
        $this->cachedRating = $cachedRating;
    }

    /**
     * @return mixed
     */
    public function getDeleted()
    {
        return $this->deleted;
    }

    /**
     * @param mixed $deleted
     */
    public function setDeleted($deleted)
    {
        $this->deleted = $deleted;
    }

    /**
     * @return mixed
     */
    public function getGpsLocation()
    {
        return $this->gpsLocation;
    }

    /**
     * @param mixed $gpsLocation
     */
    public function setGpsLocation($gpsLocation)
    {
        $this->gpsLocation = $gpsLocation;
    }

    /**
     * @return mixed
     */
    public function getPhotoFile()
    {
        return $this->photoFile;
    }

    /**
     * @param mixed $photoFile
     */
    public function setPhotoFile($photoFile)
    {
        $this->photoFile = $photoFile;
    }

    /**
     * @return mixed
     */
    public function getReports()
    {
        return $this->reports;
    }

    /**
     * @param mixed $reports
     */
    public function setReports($reports)
    {
        $this->reports = $reports;
    }

    /**
     * @return mixed
     */
    public function getStoryComments()
    {
        return $this->storyComments;
    }

    /**
     * @param mixed $storyComments
     */
    public function setStoryComments($storyComments)
    {
        $this->storyComments = $storyComments;
    }

    /**
     * @return mixed
     */
    public function getDateCreated()
    {
        return $this->dateCreated;
    }

    /**
     * @param mixed $dateCreated
     */
    public function setDateCreated($dateCreated)
    {
        $this->dateCreated = $dateCreated;
    }

    /**
     * @return mixed
     */
    public function getStoryText()
    {
        return $this->storyText;
    }

    /**
     * @param mixed $storyText
     */
    public function setStoryText($storyText)
    {
        $this->storyText = $storyText;
    }

    /**
     * @return mixed
     */
    public function getTitle()
    {
        return $this->title;
    }

    /**
     * @param mixed $title
     */
    public function setTitle($title)
    {
        $this->title = $title;
    }

    /**
     * @return mixed
     */
    public function getViews()
    {
        return $this->views;
    }

    /**
     * @param mixed $views
     */
    public function setViews($views)
    {
        $this->views = $views;
    }

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }


/********************************* END GETTERS/SETTERS *********************************/





} 