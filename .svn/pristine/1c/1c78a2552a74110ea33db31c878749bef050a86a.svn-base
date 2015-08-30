<?php
/**
 * Created by PhpStorm.
 * User: admin
 * Date: 10/1/2014
 * Time: 11:34 PM
 */

namespace GeoStory\Entity;


use Doctrine\ORM\Mapping as ORM;
use Zend\XmlRpc\Value\DateTime;
use ZfcUser\Entity\User as ZfcUserEntity;

/**
 * An User
 *
 * @ORM\Entity
 * @ORM\Table(name="user")
 */
class User extends ZfcUserEntity {



    /********************************* START ATTRIBUTES *********************************/
//    /**
//     * @ORM\Id
//     * @ORM\Column(type="integer");
//     *
//     * @ORM\GeneratedValue(strategy="AUTO")
//     */
//    protected $user_id;


    /**
     * @ORM\OneToMany(targetEntity="Story", mappedBy="author")
     **/
    protected $stories;

//    /**
//     * @ORM\OneToMany(targetEntity="View", mappedBy="user")
//     */
//    protected $viewedStories;
//
//    /**
//     * @ORM\OneToMany(targetEntity="StoryComment", mappedBy="user")
//     */
//    protected $storyComments;
//
//    /**
//     * @ORM\OneToMany(targetEntity="CommentResponse", mappedBy="user")
//     */
//    protected $commentResponses;
//
//    /**
//     * @ORM\OneToOne(targetEntity="GpsLocation")
//     * @ORM\JoinColumn(name="gpslocation_id", referencedColumnName="id")
//     **/
//    protected $gpsLocation;
//
//    /**
//     * @ORM\OneToOne(targetEntity="UserSettings")
//     * @ORM\JoinColumn(name="usersettings_id", referencedColumnName="id")
//     **/
//    protected $userSettings;


    /**
     * @ORM\ManyToMany(targetEntity="User", inversedBy="myFriends")
     * @ORM\JoinTable(name="friends",
     *      joinColumns={@ORM\JoinColumn(name="user", referencedColumnName="user_id")},
     *      inverseJoinColumns={@ORM\JoinColumn(name="user_user_id", referencedColumnName="user_id")}
     *      )
     **/
    protected $friendsWithMe;


    /**
     * @ORM\ManyToMany(targetEntity="User", mappedBy="friendsWithMe")
     **/
    protected $myFriends;

//$myFriends    /**
//     * @ORM\Column(type="string")
//     */
//    protected $userName;
//
//    /**
//     * @ORM\Column(type="string")
//     */
//    protected $hashedPassword;
//
//    /**
//     * @ORM\Column(type="boolean")
//     */
//    protected $userEnabled;

    /**
     * @ORM\Column(type="smallint")
     */
    protected $userType;

    /**
     * @ORM\Column(type="datetime")
     */
    protected $dateCreated;





    /******************************** END ATTRIBUTES *********************************/



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
    public function getMyFriends()
    {
        return $this->myFriends;
    }

    /**
     * @param mixed $myFriends
     */
    public function setMyFriends($myFriends)
    {
        $this->myFriends = $myFriends;
    }

    /**
     * @return mixed
     */
    public function getStories()
    {
        return $this->stories;
    }

    /**
     * @param mixed $stories
     */
    public function setStories($stories)
    {
        $this->stories = $stories;
    }

    /**
     * @return mixed
     */
    public function getUserType()
    {
        return $this->userType;
    }

    /**
     * @param mixed $userType
     */
    public function setUserType($userType)
    {
        $this->userType = $userType;
    }

    public function isAnonymous()
    {
        return (strpos($this->email, 'testUser') !== false);
    }


    /********************************* START GETTERS/SETTERS *********************************/



}