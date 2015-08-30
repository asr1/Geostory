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
 * @ORM\Entity
 * @ORM\Table(name="view")
 *
 */
class View {


/********************************* START ATTRIBUTES *********************************/
    /**
    * @ORM\Id
    * @ORM\Column(type="integer");
    * @ORM\GeneratedValue(strategy="AUTO")
    */
    protected $id;


    /**
     * @ORM\ManyToOne(targetEntity="User", inversedBy="views")
     * @ORM\JoinColumn(name="viewer_id", referencedColumnName="user_id")
     */
    protected $viewerAccount;


    /**
     * @ORM\ManyToOne(targetEntity="Story", inversedBy="views")
     * @ORM\JoinColumn(name="view_id", referencedColumnName="id")
     */
    protected $story;

    /**
     * @ORM\Column(type="boolean")
     */
    protected $hasRating;


    /**
     * @ORM\Column(type="integer")
     */
    protected $rating;





/******************************** END ATTRIBUTES *********************************/


    function __construct($user,$story) {
        $this->viewerAccount = $user;
        $this->story = $story;
        $this->hasRating = false;
        $this->rating = -1;
    }

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getRating()
    {
        return $this->rating;
    }

    /**
     * @param mixed $rating
     */
    public function setRating($rating)
    {
        $this->rating = $rating;
        $this->hasRating = true;
    }

    public function hasRating(){
        return $this->hasRating;
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

    /**
     * @return mixed
     */
    public function getUser()
    {
        return $this->user;
    }

    /**
     * @param mixed $user
     */
    public function setUser($user)
    {
        $this->user = $user;
    }

    /**
     * @return mixed
     */
    public function getViewerAccount()
    {
        return $this->viewerAccount;
    }

    /**
     * @param mixed $viewerAccount
     */
    public function setViewerAccount($viewerAccount)
    {
        $this->viewerAccount = $viewerAccount;
    }


/********************************* START GETTERS/SETTERS *********************************/



/********************************* END GETTERS/SETTERS *********************************/





} 